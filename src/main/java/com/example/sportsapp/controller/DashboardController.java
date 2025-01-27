package com.example.sportsapp.controller;

import com.example.sportsapp.SortUtils;
import com.example.sportsapp.model.Players;
import com.example.sportsapp.model.Team;
import com.example.sportsapp.model.User;
import com.example.sportsapp.service.PlayerService;
import com.example.sportsapp.service.TeamsService;
import com.example.sportsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Controller
public class DashboardController {

    private final UserService userService;
    private final TeamsService teamService;
    private final PlayerService playerService;

    @Autowired
    public DashboardController(UserService userService, TeamsService teamService, PlayerService playerService) {
        this.userService = userService;
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(
            @RequestParam(value = "userSort", required = false) String userSort,
            @RequestParam(value = "teamSort", required = false) String teamSort,
            @RequestParam(value = "playerSort", required = false) String playerSort,
            @RequestParam(value = "teamId", required = false) Long teamId, // Add this to filter players by team
            Model model) {

        // Validate sort fields
        if (userSort == null || userSort.isEmpty()) userSort = "id";
        if (teamSort == null || teamSort.isEmpty()) teamSort = "id";
        if (playerSort == null || playerSort.isEmpty()) playerSort = "id";
        System.out.println("teamSort parameter: " + teamSort);
        // Fetch and sort the data
        List<User> users = userService.getAllUsersSorted(userSort, true); // Always ascending
        List<Team> teams = teamService.getAllTeamsSorted(teamSort, true);
        List<Players> players = playerService.getAllPlayersSorted(playerSort, true); // Show all players if no team is selected

        // Add sorted lists to the model
        model.addAttribute("users", users);
        model.addAttribute("teams", teams);
        model.addAttribute("players", players);

        return "dashboard";
    }
}
