package com.taskmaster.services;

import com.taskmaster.models.user.AppUser;
import com.taskmaster.repositories.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = userRepository.findByEmail(email);

        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        AppUser user = optionalUser.get();

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getAuthorities());
    }
}
