package com.taskmaster.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskmaster.config.JwtUtil;
import com.taskmaster.models.company.CompanyDetails;
import com.taskmaster.models.response.AuthenticationRequest;
import com.taskmaster.models.response.AuthenticationResponse;
import com.taskmaster.models.user.AppUser;
import com.taskmaster.models.user.UserDTO;
import com.taskmaster.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public AuthenticationResponse registerUser(UserDTO userDTO) {
        Optional<AppUser> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email is already taken");
        }

        AppUser user = new AppUser();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setCompanyName(userDTO.getCompanyName());
        user.setCompanyDetails(userDTO.getCompanyDetails());

        String role = userDTO.getRole();
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        user.setRole(role);
        userRepository.save(user);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt, user);
    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        AppUser user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new AuthenticationResponse(jwt, user);
    }

    public UserDTO updateUserFields(Long id, Map<String, Object> updates, String currentUsername) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!user.getUsername().equals(currentUsername)) {
            throw new SecurityException("You are not authorized to update this user");
        }

        updates.forEach((field, value) -> {
            switch (field) {
                case "firstName":
                    user.setFirstName((String) value);
                    break;
                case "lastName":
                    user.setLastName((String) value);
                    break;
                case "email":
                    user.setEmail((String) value);
                    break;
                case "phoneNumber":
                    user.setPhoneNumber((String) value);
                    break;
                case "companyName":
                    user.setCompanyName((String) value);
                    break;
                case "role":
                    user.setRole((String) value);
                    break;
                case "companyDetails":
                    user.setCompanyDetails((CompanyDetails) value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown field: " + field);
            }
        });

        userRepository.save(user);

        // Generate new JWT with the updated username if username was changed
        String newToken = jwtUtil.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getAuthorities()));

        return new UserDTO(user, newToken);
    }
}
