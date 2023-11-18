package com.malek.app.rest.Controller;

import com.malek.app.rest.Model.User;
import com.malek.app.rest.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String hello() {

        return "Hello World";
    }
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id) {
        return userRepo.findById(id).get();
    }

    @PostMapping("/save")
    public String saveUser(User user) {
        userRepo.save(user);
        return "User saved";
    }
    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, User user) {
        User currentUser = userRepo.findById(id).get();

        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setStatus(user.isStatus());
        currentUser.setStatus(user.isStatus());

        userRepo.save(currentUser);

        return "User updated";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userRepo.deleteById(id);
        return "User deleted";
    }

}
