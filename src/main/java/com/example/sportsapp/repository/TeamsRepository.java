package com.example.sportsapp.repository;

import com.example.sportsapp.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TeamsRepository extends JpaRepository<Team, Long> {

    // Correct method to return Optional<User>
    Optional<Team> findByName(String name);
}
