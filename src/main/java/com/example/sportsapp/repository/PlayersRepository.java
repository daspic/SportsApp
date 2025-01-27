package com.example.sportsapp.repository;

import com.example.sportsapp.model.Players;
import com.example.sportsapp.model.Team;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayersRepository extends JpaRepository<Players, Long> {

    // Fetch a single player by their ID
    Optional<Players> findById(Long id);

    List<Players> findAllByTeam(Team team, Sort sort);

    List<Players> findAll(Sort sort);
}
