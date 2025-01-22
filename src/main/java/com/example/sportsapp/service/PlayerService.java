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

    public List<Players> getAllPlayers(){
        return playersRepository.findAll();
    }

    public Optional<Players> findById(Long id) {
        return playersRepository.findById(id);
    }

    public void save(Players players) {
        playersRepository.save(players);
    }

    public String registerPlayers(Players players) {
        Optional<Players> existingPlayers = findById(players.getPlayer_id());
        if (existingPlayers.isPresent()) {
            return "Name already taken";
        }
        save(players); // Save the user after password encoding
        return "Player registered successfully";
    }

}
