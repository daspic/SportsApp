package com.example.sportsapp.service;

import com.example.sportsapp.model.Player;
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
    public List<Player> getAllPlayers() {
        return playersRepository.findAll();
    }

    // Fetch a single player by their ID
    public Optional<Player> findById(Long id) {
        return playersRepository.findById(id);
    }

    // Save or update a player
    public void save(Player player) {
        playersRepository.save(player);
    }

    // Register a new player with a unique ID check
    public String registerPlayers(Player players) {
        Optional<Player> existingPlayer = findById(players.getId());

        // Use ifPresent for cleaner check
        existingPlayer.ifPresent(player -> {
            throw new IllegalArgumentException("Player ID already taken");
        });

        save(players);
        return "Player registered successfully";
    }

    public List<Player> getAllPlayersSorted(String sortField, boolean ascending) {
        Sort sort;

        // Check if the sort field is "user.name" (for example)
        if ("user.name".equals(sortField)) {
            sort = Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, "user.name");
        } else {
            sort = Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, "id");
        }

        return playersRepository.findAll(sort);
    }

    public List<Player> getPlayersByTeamSorted(Team team, String sortField, boolean ascending) {
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
