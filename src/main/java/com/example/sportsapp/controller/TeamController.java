package com.example.sportsapp.controller;

import com.example.sportsapp.model.Team;
import com.example.sportsapp.model.User;
import com.example.sportsapp.service.TeamsService;
import com.example.sportsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TeamController {
    @Autowired
    private TeamsService teamService;

    @GetMapping("/dashboard")
    public String getTeams(Model model) {
        List<Team> teamsList = teamService.getAllTeams();
        model.addAttribute("teams", teamsList);
        model.addAttribute("message", "This is a message!");

        return "dashboard";  // Thymeleaf template
    }

}
