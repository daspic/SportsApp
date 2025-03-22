package com.example.sportsapp.controller;

import com.example.sportsapp.model.Player;
import com.example.sportsapp.model.Team;
import com.example.sportsapp.service.PlayerService;
import com.example.sportsapp.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TeamController {

    private TeamsService teamService;
    private PlayerService playerService;

    @Autowired
    public TeamController(TeamsService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping("/teams/{id}")
    public String getTeamById(
            @PathVariable("id") Long teamId,
            @RequestParam(value = "playerSort", required = false, defaultValue = "id") String playerSort,
            @RequestParam(value = "playerPage", required = false, defaultValue = "0") int playerPage,
            @RequestParam(value = "playerSize", required = false, defaultValue = "10") int playerSize,
            @RequestParam(value = "from", required = false, defaultValue = "dashboard") String from,
            @RequestParam(value = "playerId", required = false) Long playerId,
            Model model) {

        Team team = teamService.getTeamById(teamId);
        if (team == null) {
            return "error"; // Show an error page if the team is not found
        }

        if (playerSort == null || playerSort.isEmpty()) playerSort = "id";

        Pageable pageable = PageRequest.of(playerPage, playerSize, Sort.by(playerSort)); // Create Pageable object
        Page<Player> playerPageData = playerService.getPlayersByTeam(team, pageable); // Use Pageable in your service method

        model.addAttribute("team", team);
        model.addAttribute("playerPageData", playerPageData);
        model.addAttribute("playerPage", playerPage);
        model.addAttribute("playerSize", playerSize);
        model.addAttribute("playerSort", playerSort);
        model.addAttribute("from", from);

        // Fetch and add the player to the model if playerId is provided
        if (playerId != null) {
            Player player = playerService.getPlayerById(playerId);
            model.addAttribute("player", player);
        }

        return "teamDetails"; // The view to display a single team's details
    }

    @PostMapping("/teams")
    public String addTeam(@ModelAttribute Team team,
                          @RequestParam(value = "userPage", defaultValue = "0") int userPage,
                          @RequestParam(value = "userSize", defaultValue = "10") int userSize,
                          @RequestParam(value = "teamPage", defaultValue = "0") int teamPage,
                          @RequestParam(value = "teamSize", defaultValue = "10") int teamSize,
                          @RequestParam(value = "playerPage", defaultValue = "0") int playerPage,
                          @RequestParam(value = "playerSize", defaultValue = "10") int playerSize,
                          RedirectAttributes redirectAttributes) {

        try {
            teamService.createTeam(team);
            redirectAttributes.addFlashAttribute("successAddTeam", "Team added successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorAddTeam", e.getMessage());
        }
//        teamService.createTeam(team);

        return "redirect:/dashboard?userPage=" + userPage + "&userSize=" + userSize +
                "&teamPage=" + teamPage + "&teamSize=" + teamSize +
                "&playerPage=" + playerPage + "&playerSize=" + playerSize;
    }

    @PostMapping("/teams/{id}/add-player")
    public String addPlayerToTeam(
            @PathVariable Long id,
            @RequestParam String email,
            @RequestParam String playerStats,
            RedirectAttributes redirectAttributes) {

        try {
            // Ensure the player stats field is not empty
//            if (playerStats == null || playerStats.trim().isEmpty()) {
//                throw new IllegalArgumentException("Player stats must be provided.");
//            }

            // Call the service method to add the player
            teamService.addPlayerToTeam(playerStats, id, email);

            // Success message
            redirectAttributes.addFlashAttribute("success", "Player added successfully!");
        } catch (Exception e) {
            // Error message
            redirectAttributes.addFlashAttribute("error", "Error adding player: " + e.getMessage());
        }

        return "redirect:/teams/" + id + "?from=teams";
    }
}
