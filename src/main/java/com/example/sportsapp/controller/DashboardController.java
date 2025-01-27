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
            Model model) {

        // Validate sort fields
        if (userSort == null || userSort.isEmpty()) userSort = "name";
        if (teamSort == null || teamSort.isEmpty()) teamSort = "teamId";
        if (playerSort == null || playerSort.isEmpty()) playerSort = "id";

        // Fetch and sort the data
        List<User> users = userService.getAllUsersSorted(userSort, true); // Always ascending
        List<Team> teams = teamService.getAllTeamsSorted(teamSort, true);
        List<Players> players = playerService.getAllPlayersSorted(playerSort, true);


//        // Fetch the list of users, teams, and players
//        List<User> users = userService.getAllUsers();
//        List<Team> teams = teamService.getAllTeams();
//        List<Players> players = playerService.getAllPlayers();

//        // Apply sorting if sorting parameters are provided
//        if (userSort != null) {
//            SortUtils.sortList(users, userSort, Comparator.comparing(User::getName), Comparator.comparing(User::getUser_id));
//        }
//
//        if (teamSort != null) {
//            SortUtils.sortList(teams, teamSort, Comparator.comparing(Team::getName), Comparator.comparing(Team::getTeam_id));
//        }
//
//        if (playerSort != null) {
//            SortUtils.sortList(players, playerSort, Comparator.comparing(p -> p.getUser().getName()), Comparator.comparing(Players::getPlayer_id));
//        }

        // Add sorted lists to the model
        model.addAttribute("users", users);
        model.addAttribute("teams", teams);
        model.addAttribute("players", players);

        return "dashboard";
    }
}
