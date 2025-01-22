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

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login"; // Your login page
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

    @PostMapping("/login")
    public String logInUser(User users) {
        // Find the user by email
        Optional<User> existingUser = userService.findByEmail(users.getEmail());

        // Check if the user exists and validate the password
        if (existingUser.isPresent()) {
            User user = existingUser.get();

            // Log the raw and encoded password for debugging (optional)
            System.out.println("Raw password: " + users.getPassword());
            System.out.println("Stored encoded password: " + user.getPassword());

            // Use PasswordEncoder to validate the password
            if (userService.getPasswordEncoder().matches(users.getPassword(), user.getPassword())) {
                return "redirect:/dashboard";
            }
        }

        // If user does not exist or password is invalid, return an error
        return "Error";
    }
}
