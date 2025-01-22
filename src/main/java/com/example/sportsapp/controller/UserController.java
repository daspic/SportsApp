package com.example.sportsapp.controller;

import com.example.sportsapp.model.Team;
import com.example.sportsapp.model.User;
import com.example.sportsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";  // Thymeleaf template
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User users) {
        userService.registerUser(users);
        return "redirect:/login";
    }

//    @GetMapping("/dashboard")
//    public String getUsers(Model model) {
//        List<User> usersList = userService.getAllUsers();
//        model.addAttribute("users", usersList);
//        model.addAttribute("message", "This is a message!");
//
//        return "dashboard";  // Thymeleaf template
//    }
}
