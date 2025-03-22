package com.example.sportsapp.service;

import com.example.sportsapp.model.Player;
import com.example.sportsapp.model.Team;
import com.example.sportsapp.model.User;
import com.example.sportsapp.repository.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private final PlayersRepository playersRepository;
    private final UserService userService;
    private final TeamsService teamService;

    @Autowired
    public PlayerService(PlayersRepository playersRepository, UserService userService, TeamsService teamService) {
        this.playersRepository = playersRepository;
        this.userService = userService;
        this.teamService = teamService;
    }

    public void updateProfilePicture(Long playerId, String filePath) {
        Optional<Player> optionalPlayer = playersRepository.findById(playerId);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.setProfilePicture(filePath);
            playersRepository.save(player);
        } else {
            throw new RuntimeException("Player not found");
        }
    }

    // Save or update a player
    public void save(Player player) {
        playersRepository.save(player);
    }

    public Page<Player> getAllPlayersPaginated(Pageable pageable){
        return playersRepository.findAll(pageable);
    }

    public Page<Player> getPlayersByTeam(Team team, Pageable pageable) {
        return playersRepository.findByTeam(team, pageable);
    }

    public String addUserAsPlayer(String userEmail, String teamName, String playerStats) {
        Optional<User> optionalUser = userService.findByEmail(userEmail);
        if (optionalUser.isEmpty()) {
            return "Error: User not found.";
        }

        User user = optionalUser.get();
        if (!user.getRole().equalsIgnoreCase("player")) {
            return "Error: User '" + userEmail + "' must have the 'PLAYER' role to be added.";
        }

        Optional<Team> optionalTeam = teamService.findByNameIgnoreCase(teamName);
        if (optionalTeam.isEmpty()) {
            return "Error: Team '" + teamName + "' was not found.";
        }

        Team team = optionalTeam.get();
        if (playersRepository.existsByUserAndTeam(user, team)) {
            return "Error: Player is already in team '" + teamName.substring(0, 1).toUpperCase() + teamName.substring(1) + "'.";
        }

        // Create and save the new Player entity
        Player player = new Player();
        player.setUser(user);
        player.setTeam(team);
        player.setPlayerStats(playerStats);
        playersRepository.save(player);

        return "Player successfully added to team '" + teamName.substring(0, 1).toUpperCase() + teamName.substring(1) + "'.";
    }

    public Optional<Player> findById(Long id) {
        return playersRepository.findById(id);
    }

    public Player getPlayerById(Long id) {
        Optional<Player> optionalPlayer = playersRepository.findById(id);
        return optionalPlayer.orElse(null);
    }
}
