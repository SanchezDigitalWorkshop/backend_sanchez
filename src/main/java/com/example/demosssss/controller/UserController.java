package com.example.demosssss.controller;

import com.example.demosssss.model.UpdateProfileRequest;
import com.example.demosssss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/me")
    public ResponseEntity<?> getProfile(@RequestParam String dni) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authentication: " + authentication);
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        String email = authentication.getName();
        //return ResponseEntity.ok(Map.of("email", email));
        return userService.getUserProfile(dni);
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request, HttpServletRequest httpRequest) {
        return userService.updateUserProfile(request, httpRequest);
    }
}