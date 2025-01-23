package com.example.sportsapp.controller;

import com.example.sportsapp.model.Players;
import com.example.sportsapp.model.Team;
import com.example.sportsapp.service.PlayerService;
import com.example.sportsapp.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TeamController {

    @Autowired
    private TeamsService teamService;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/teams/{id}")
    public String getTeamById(@PathVariable("id") Long teamId, Model model) {
        Team team = teamService.getTeamById(teamId);
        if (team == null) {
            return "error"; // Show an error page if the team is not found
        }

        // Fetch players associated with the team
        List<Players> players = playerService.getPlayersByTeamId(teamId);

        // Add team and players to the model
        model.addAttribute("team", team);
        model.addAttribute("players", players);

        return "teamDetails"; // The view to display a single team's details
    }
}
