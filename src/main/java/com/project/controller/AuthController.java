package com.project.controller;

import com.project.entity.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            return "Email already registered";
        }

        user.setRole("USER");
        userRepository.save(user);

        return "Registration successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {
        User user = userRepository.findByEmail(loginUser.getEmail());

        if (user == null) {
            return "User not found";
        }

        if (user.getPassword().equals(loginUser.getPassword())) {
            return "Login successful:" + user.getId();
        } else {
            return "Invalid password";
        }
    }
}