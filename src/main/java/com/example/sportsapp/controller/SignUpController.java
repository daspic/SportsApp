package com.example.sportsapp.controller;

import com.example.sportsapp.model.User;
import com.example.sportsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class SignUpController {

    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // Your sign-up page
    }

    @PostMapping("/signup")
    public String signUpUser(User users) {
        // Check if the email already exists using Optional's isPresent()
        Optional<User> existingUser = userService.findByEmail(users.getEmail());
        if (existingUser.isPresent()) {
            // If the email exists, show an error message or redirect
            return "redirect:/signup?error"; // Example: Redirect with an error
        }

        // Otherwise, save the new user
        userService.save(users);
        return "redirect:/login"; // Redirect to the login page after successful signup
    }
}
