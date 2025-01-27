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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TeamController {

    private TeamsService teamService;
    private PlayerService playerService;
    private Team team;

    @Autowired
    public TeamController(TeamsService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping("/teams/{id}")
    public String getTeamById(
            @PathVariable("id") Long teamId,
            @RequestParam(value = "playerSort", required = false) String playerSort,
            Model model) {

        Team team = teamService.getTeamById(teamId);
        if (team == null) {
            return "error"; // Show an error page if the team is not found
        }

        // Fetch players only for the selected team, and sort them by the specified field
        List<Players> players = playerService.getPlayersByTeamSorted(
                team,
                playerSort != null ? playerSort : "id", // Default to sorting by player ID
                true // You can set this to false for descending order if needed
        );
        model.addAttribute("team", team);
        model.addAttribute("players", players);

        return "teamDetails"; // The view to display a single team's details
    }
}
