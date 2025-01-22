package com.example.sportsapp.repository;

import com.example.sportsapp.model.Players;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayersRepository extends JpaRepository<Players, Long> {

    // Correct method to return Optional<Players>
    Optional<Players> findById(Long id);
}
