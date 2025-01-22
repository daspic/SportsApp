package com.example.sportsapp.controller;

import com.example.sportsapp.model.Team;
import com.example.sportsapp.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TeamController {
    @Autowired
    private TeamsService teamService;

    @GetMapping("/teams/{id}")
    public String getTeamById(@PathVariable("id") Long teamId, Model model) {
        Team team = teamService.getTeamById(teamId);
        if (team == null) {
            return "error"; // Show an error page if the team is not found
        }
        model.addAttribute("team", team);
        return "teamDetails"; // The view to display a single team's details
    }
}
