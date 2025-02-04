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

import java.util.List;
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
        return teamsRepository.save(team);
    }

    public void addPlayerToTeam(Long teamId, String email) {
        // Find the team by ID
        Team team = teamsRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        // Find the user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find the existing player for this user
        Player player = playersRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        // Check if the player is already part of the team
        if (playersRepository.existsByUserAndTeam(user, team)) {
            throw new RuntimeException("Player is already in this team");
        }

        // Associate the player with the team (this should not trigger recursion now)
        player.setTeam(team);

        // Save the updated player without modifying the id (it will retain the same ID)
        playersRepository.save(player);
    }


    public Optional<Team> findByNameIgnoreCase(String teamName) {
        return teamsRepository.findByNameIgnoreCase(teamName);
    }
}
