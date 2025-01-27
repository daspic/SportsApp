package com.example.sportsapp.repository;

import com.example.sportsapp.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Correct method to return Optional<User>
    Optional<User> findByEmail(String email);

    List<User> findAll(Sort sort);
}
