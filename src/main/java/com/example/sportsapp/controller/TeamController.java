package com.example.sportsapp.controller;

import com.example.sportsapp.model.Player;
import com.example.sportsapp.model.Team;
import com.example.sportsapp.service.PlayerService;
import com.example.sportsapp.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @RequestParam(value = "playerSort", required = false, defaultValue = "id") String playerSort,
            @RequestParam(value = "playerPage", required = false, defaultValue = "0") int playerPage,
            @RequestParam(value = "playerSize", required = false, defaultValue = "10") int playerSize,
            @RequestParam(value = "from", required = false, defaultValue = "dashboard") String from,
            Model model) {

        Team team = teamService.getTeamById(teamId);
        if (team == null) {
            return "error"; // Show an error page if the team is not found
        }

        // Fetch paginated and sorted players for the team
        Page<Player> playerPageData = playerService.getPlayersByTeamPaginated(
                team, playerSort, playerPage, playerSize, true // Sort ascending
        );

        model.addAttribute("team", team);
        model.addAttribute("playerPageData", playerPageData);
        model.addAttribute("playerPage", playerPage);
        model.addAttribute("playerSize", playerSize);
        model.addAttribute("playerSort", playerSort);
        model.addAttribute("from", from);

        return "teamDetails"; // The view to display a single team's details
    }

    @PostMapping("/teams")
    public String addTeam(@ModelAttribute Team team) {
        teamService.createTeam(team);
        return String.format("redirect:/teams/%s", team.getId());  // Redirects back to dashboard
    }

    @PostMapping("/teams/{id}/add-player")
    public String addPlayerToTeam(@PathVariable Long id, @RequestParam String email, RedirectAttributes redirectAttributes) {
        try {
            teamService.addPlayerToTeam(id, email);
            redirectAttributes.addFlashAttribute("success", "Player added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding player: " + e.getMessage());
        }
        return "redirect:/teams/" + id;
    }
}
