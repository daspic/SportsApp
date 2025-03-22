package com.example.sportsapp.service;

import com.example.sportsapp.model.Player;
import com.example.sportsapp.model.Team;
import com.example.sportsapp.model.User;
import com.example.sportsapp.repository.PlayersRepository;
import com.example.sportsapp.repository.TeamsRepository;
import com.example.sportsapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Service
public class TeamsService {

    private final TeamsRepository teamsRepository;
    private final UserRepository userRepository;
    private final PlayersRepository playersRepository;

    @Autowired
    public TeamsService(TeamsRepository teamsRepository, UserRepository userRepository, PlayersRepository playersRepository) {
        this.teamsRepository = teamsRepository;
        this.userRepository = userRepository;
        this.playersRepository = playersRepository;
    }

    // Fetch a team by its ID
    public Team getTeamById(Long teamId) {
        return teamsRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found with ID: " + teamId));
    }

    public Page<Team> getAllTeamsPaginated(Pageable pageable) {
        return teamsRepository.findAll(pageable);
    }

    public Team createTeam(Team team) {
        Optional<Team> existingTeam = teamsRepository.findByNameIgnoreCase(team.getName());
        if (existingTeam.isPresent()) {
            throw new IllegalArgumentException("A team with this name already exists.");
        }
        return teamsRepository.save(team);
    }

    public void addPlayerToTeam(
            @RequestParam("playerStats") String playerStats,
            Long teamId, String email) {
        // Fetch the team
        Team team = teamsRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found."));

        // Fetch the user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        // Check if the user already exists in the team
        boolean playerExists = playersRepository.existsByUserAndTeam(user, team);
        if (playerExists) {
            throw new IllegalArgumentException("This player is already in the team.");
        }

        // Check if the user has the "player" role
        if (!user.getRole().equalsIgnoreCase("player")) {
            throw new IllegalArgumentException("User is not a player.");
        }

        // Create and save the new Player
        Player player = new Player();
        player.setUser(user);
        player.setTeam(team);
        player.setPlayerStats(playerStats); // Set player stats

        playersRepository.save(player);
    }


    public Optional<Team> findByNameIgnoreCase(String teamName) {
        return teamsRepository.findByNameIgnoreCase(teamName);
    }
}
