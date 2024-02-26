package com.spring.implementation.controller;

import com.spring.implementation.DTO.UserDTO;
import com.spring.implementation.model.User;
import com.spring.implementation.service.UserDetailsServiceImpl;
import com.spring.implementation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/v1/api/")
public class DashboardController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {

        if(userService.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        if(userDTO.getEmail().isEmpty() || userDTO.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }

        String emailAddress = userDTO.getEmail();
        String regexPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(emailAddress);

        boolean isValid = matcher.matches();
        if(!isValid) {
            return ResponseEntity.badRequest().body("Invalid email address");
        }

        User user = userDetailsService.save(userDTO);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        User user = userService.findByEmail(userDTO.getEmail());
        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
        if(passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }


}
