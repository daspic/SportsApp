package com.example.sportsapp.service;

import com.example.sportsapp.model.Players;
import com.example.sportsapp.model.Team;
import com.example.sportsapp.repository.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    // Save or update a player
    public void save(Players players) {
        playersRepository.save(players);
    }

    // Register a new player with a unique ID check
    public String registerPlayers(Players players) {
        Optional<Players> existingPlayer = findById(players.getId());

        // Use ifPresent for cleaner check
        existingPlayer.ifPresent(player -> {
            throw new IllegalArgumentException("Player ID already taken");
        });

        save(players);
        return "Player registered successfully";
    }

    public List<Players> getAllPlayersSorted(String sortField, boolean ascending) {
        Sort sort;

        // Check if the sort field is "user.name" (for example)
        if ("user.name".equals(sortField)) {
            sort = Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, "user.name");
        } else {
            sort = Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, "id");
        }

        return playersRepository.findAll(sort);
    }

    public List<Players> getPlayersByTeamSorted(Team team, String sortField, boolean ascending) {
        Sort sort;

        // Check if the sort field is "user.name" or other valid fields
        if ("user.name".equals(sortField)) {
            sort = Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, "user.name");
        } else {
            sort = Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        }

        // Fetch players for the given team and sorted by the given field
        return playersRepository.findAllByTeam(team, sort);
    }
}
