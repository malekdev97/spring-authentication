package com.malek.app.rest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(request -> request.getMethod().equals("OPTIONS")).authenticated()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

/*
@Bean
public UserDetailsService users() {
    return new InMemoryUserDetailsManager(
            User.withUsername("user")
                    .password("{noop}password")
                    .roles("USER")
                    .build());
        }
 */
    @Bean
    public UserDetailsService users() {
        // Create a UserDetails object for admin
        UserDetails admin = User.builder().username("admin").password("password").roles("ADMIN").build();
        // Create a UserDetails object for user
        UserDetails user = User.builder().username("user").password("password").roles("USER").build();

        // Return a UserDetailsService
        return new InMemoryUserDetailsManager(admin, user);
    }



}