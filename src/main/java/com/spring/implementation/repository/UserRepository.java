package com.spring.implementation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.implementation.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

     
    public User findByResetPasswordToken(String token);

    public boolean existsByEmail(String email);
}
