package com.ltp.gradesubmission.security.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.security.SecurityConstants;
import com.ltp.gradesubmission.security.manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /*
     * attemptAuthentication: This method is called when the user tries to login.
     * authenticationManager: This is the interface that provides the facility to authenticate a user.
     * successfulAuthentication: This method is called when a user is successfully authenticated. Later on, a token is generated and sent to the user.
     * 
     */

    private AuthenticationManager authenticationManager;
 
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            // Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Password: " + user.getPassword());
            System.out.println("Seccessful authentication");

            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            System.out.println("Bad request");
            throw new RuntimeException();

         } 

        // return super.attemptAuthentication(request, response);
 
    }


}
