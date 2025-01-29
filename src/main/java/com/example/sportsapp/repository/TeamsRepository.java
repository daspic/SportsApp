package com.example.sportsapp.repository;

import com.example.sportsapp.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamsRepository extends JpaRepository<Team, Long> {

    // Correct method to return Optional<User>
    Optional<Team> findByName(String name);

    Page<Team> findAll(Pageable pageable);
}
