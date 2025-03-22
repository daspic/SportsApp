package com.example.sportsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/")
    public String home() {
        return "mainPage"; // Returns the index.html page
    }
}
