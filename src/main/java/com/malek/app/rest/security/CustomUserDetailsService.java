package com.malek.app.rest.security;

import com.malek.app.rest.Model.Role;
import com.malek.app.rest.Model.UserEntity;
import com.malek.app.rest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

        return new User(user.getUsername(), user.getPassword(), mapRoleToAuthorities(user.getRole()));
    }

    // helper method to get the authorities of a user by converting list to role
    private Collection<GrantedAuthority> mapRoleToAuthorities(List<Role> role) {
        return role.stream().map(roles -> new SimpleGrantedAuthority(roles .getName())).collect(Collectors.toList());
    }
}