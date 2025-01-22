package com.example.sportsapp.controller;

import com.example.sportsapp.service.PlayerService;
import com.example.sportsapp.service.TeamsService;
import com.example.sportsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final UserService userService;
    private final TeamsService teamService;
    private PlayerService playerService;

    @Autowired
    public DashboardController(UserService userService, TeamsService teamService, PlayerService playerService) {
        this.userService = userService;
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("teams", teamService.getAllTeams());
        model.addAttribute("players", playerService.getAllPlayers());
        return "dashboard";
    }
}

