package com.example.sportsapp.service;

import com.example.sportsapp.model.Players;
import com.example.sportsapp.repository.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayersRepository playersRepository;

    @Autowired
    public PlayerService(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    // Fetch all players
    public List<Players> getAllPlayers() {
        return playersRepository.findAll();
    }

    // Fetch a single player by their ID
    public Optional<Players> findById(Long id) {
        return playersRepository.findById(id);
    }

    // Fetch all players belonging to a specific team
    public List<Players> getPlayersByTeamId(Long teamId) {
        return playersRepository.findByTeam_teamId(teamId);
    }

    // Save or update a player
    public void save(Players players) {
        playersRepository.save(players);
    }

    // Register a new player with a unique ID check
    public String registerPlayers(Players players) {
        Optional<Players> existingPlayer = findById(players.getPlayer_id());

        // Use ifPresent for cleaner check
        existingPlayer.ifPresent(player -> {
            throw new IllegalArgumentException("Player ID already taken");
        });

        save(players);
        return "Player registered successfully";
    }
}
