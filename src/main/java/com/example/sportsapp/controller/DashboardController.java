package com.example.sportsapp.controller;

import com.example.sportsapp.model.Player;
import com.example.sportsapp.model.Team;
import com.example.sportsapp.model.User;
import com.example.sportsapp.service.PlayerService;
import com.example.sportsapp.service.TeamsService;
import com.example.sportsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            Model model,
            @RequestParam(value = "userSort", required = false, defaultValue = "id") String userSort,
            @RequestParam(value = "userPage", defaultValue = "0") int userPage,
            @RequestParam(value = "userSize", defaultValue = "10") int userSize,
            @RequestParam(value = "teamSort", required = false, defaultValue = "id") String teamSort,
            @RequestParam(value = "teamPage", defaultValue = "0") int teamPage,
            @RequestParam(value = "teamSize", defaultValue = "10") int teamSize,
            @RequestParam(value = "playerSort", required = false, defaultValue = "id") String playerSort,
            @RequestParam(value = "playerPage", defaultValue = "0") int playerPage,
            @RequestParam(value = "playerSize", defaultValue = "10") int playerSize
    ) {
        // Fetch paginated data
        Pageable userPageable = PageRequest.of(userPage, userSize, Sort.by(userSort));
        Page<User> userPageData = userService.getAllUsersPaginated(userPageable);

        Pageable teamPageable = PageRequest.of(teamPage, teamSize, Sort.by(teamSort));
        Page<Team> teamPageData = teamService.getAllTeamsPaginated(teamPageable);

        model.addAttribute("newTeam", new Team()); // Provide an empty team for form

        // Validate sort fields
        Pageable playerPageable = PageRequest.of(playerPage, playerSize, Sort.by(playerSort));
        Page<Player> playerPageData = playerService.getAllPlayersPaginated(playerPageable);

        // Add to model
        model.addAttribute("userPageData", userPageData);
        model.addAttribute("teamPageData", teamPageData);
        model.addAttribute("playerPageData", playerPageData);

        // Current pages and sizes
        model.addAttribute("userPage", userPage);
        model.addAttribute("userSize", userSize);
        model.addAttribute("userSort", userSort);

        model.addAttribute("teamPage", teamPage);
        model.addAttribute("teamSize", teamSize);
        model.addAttribute("teamSort", teamSort);

        model.addAttribute("playerPage", playerPage);
        model.addAttribute("playerSize", playerSize);
        model.addAttribute("playerSort", playerSort);

        return "dashboard";
    }

    @GetMapping("/users")
    public String showUsersDashboard(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page, // Current page (default: 0)
            @RequestParam(value = "size", defaultValue = "10") int size, // Users per page (default: 10)
            @RequestParam(value = "userSort", required = false) String userSort) {

        if (userSort == null || userSort.isEmpty()) userSort = "id";

//        // Fetch and sort users (if needed)
//        List<User> users = userService.getAllUsersSorted(userSort, true);
//        model.addAttribute("users", users);

        Pageable pageable = PageRequest.of(page, size, Sort.by(userSort));
        Page<User> userPage = userService.getAllUsersSortedPaginated(pageable);

        model.addAttribute("userPage", userPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("userSort", userSort);

        return "Users"; // Render the users.html template
    }

    @GetMapping("/teams")
    public String showTeamsDashboard(
            Model model,
            @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "teamSort", required = false) String teamSort){

        if (teamSort == null || teamSort.isEmpty()) teamSort = "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(teamSort)); // Create Pageable object
        Page<Team> teamPage = teamService.getAllTeamsPaginated(pageable); // Use Pageable in your service method

        model.addAttribute("teamPage", teamPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("teamSort", teamSort);

        return "teams"; // Return the name of your Thymeleaf template
    }

    @GetMapping("/players")
    public String showPlayersDashboard(
            Model model,
            @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "playerSort", required = false) String playerSort){

        if (playerSort == null || playerSort.isEmpty()) playerSort = "id";

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(playerSort));
        Page<Player> playerPage = playerService.getAllPlayersPaginated(pageable); // Use Pageable in your service

        model.addAttribute("playerPage", playerPage);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("playerSort", playerSort);

        return "players";
    }
}
