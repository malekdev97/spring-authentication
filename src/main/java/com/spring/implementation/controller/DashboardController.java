package com.spring.implementation.controller;

import com.spring.implementation.DTO.UserDTO;
import com.spring.implementation.helper.Utility;
import com.spring.implementation.model.User;
import com.spring.implementation.service.UserDetailsServiceImpl;
import com.spring.implementation.service.UserService;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    public void sendEmail(String recipientEmail, String link) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("no-repy@ameen.sa", "Forgot Password");
        helper.setTo(recipientEmail);

        String subject = "Reset password link";
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, or you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);

    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody UserDTO userDTO, HttpServletRequest request) {
        String email = userDTO.getEmail();
        String token = RandomString.make(30);

        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset-password?token=" + token; // generate reset password link

            sendEmail(email, resetPasswordLink);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while sending email");
        }

        return ResponseEntity.ok("We have sent a reset password link to your email. Please check your email.");

    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(HttpServletRequest request, @RequestParam String token, @RequestBody UserDTO userDTO) {
        // String token = request.getParameter("token");
        String password = userDTO.getPassword();

        User user = userService.getByResetPasswordToken(token);
        if(token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        User userToken = userService.getByResetPasswordToken(token);


        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        } else {
            userService.updatePassword(user, password);
            return ResponseEntity.ok("Password updated successfully");
        }
    }



}
