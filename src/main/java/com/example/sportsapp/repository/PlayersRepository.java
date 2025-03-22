package com.example.sportsapp.repository;

import com.example.sportsapp.model.Player;
import com.example.sportsapp.model.Team;
import com.example.sportsapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PlayersRepository extends JpaRepository<Player, Long> {

    // Fetch a single player by their ID
    Optional<Player> findById(Long id);

    Page<Player> findAll(Pageable pageable);

    Page<Player> findByTeam(Team team, Pageable pageable);

    boolean existsByUserAndTeam(User user, Team team);
}
