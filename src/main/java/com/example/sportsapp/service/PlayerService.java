package com.example.sportsapp.service;

import com.example.sportsapp.model.Player;
import com.example.sportsapp.model.Team;
import com.example.sportsapp.model.User;
import com.example.sportsapp.repository.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
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

    // Save or update a player
    public void save(Player player) {
        playersRepository.save(player);
    }

    public Page<Player> getAllPlayersPaginated(Pageable pageable) {
        return playersRepository.findAll(pageable);
    }

    public Page<Player> getPlayersByTeamPaginated(Team team, String sortBy, int page, int size, boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return playersRepository.findByTeam(team, pageable);
    }
}
