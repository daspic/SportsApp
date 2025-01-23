package com.example.sportsapp.repository;

import com.example.sportsapp.model.Players;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayersRepository extends JpaRepository<Players, Long> {

    // Fetch a single player by their ID
    Optional<Players> findById(Long id);

    // Fetch all players belonging to a specific team
    List<Players> findByTeam_teamId(Long teamId);
}
