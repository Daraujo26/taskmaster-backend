package com.taskmaster.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmaster.models.user.UserDTO;
import com.taskmaster.config.JwtUtil;
import com.taskmaster.services.UserService;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PutMapping("/{id}/update")
    public ResponseEntity<UserDTO> updateUserFields(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates,
            @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7); // Remove "Bearer " prefix
        String currentEmail = jwtUtil.extractEmail(jwtToken);

        UserDTO userDTO = userService.updateUserFields(id, updates, currentEmail);
        return ResponseEntity.ok(userDTO);
    }
}
