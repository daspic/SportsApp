package com.example.sportsapp.service;

import com.example.sportsapp.model.Team;
import com.example.sportsapp.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TeamsService {

    private final TeamsRepository teamsRepository;

    @Autowired
    public TeamsService(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    // Fetch all teams
    public List<Team> getAllTeams() {
        return teamsRepository.findAll();
    }

    // Fetch a team by name
    public Optional<Team> findByName(String name) {
        return teamsRepository.findByName(name);
    }

    // Save or update a team
    public void save(Team team) {
        teamsRepository.save(team);
    }

    // Register a new team with a unique name check
    public void registerTeam(Team team) {
        Optional<Team> existingTeam = findByName(team.getName());

        // Throw exception if the name already exists
        existingTeam.ifPresent(t -> {
            throw new IllegalArgumentException("Team name already taken");
        });

        save(team); // Save the team after validation
    }

    // Fetch a team by its ID
    public Team getTeamById(Long teamId) {
        return teamsRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found with ID: " + teamId));
    }

    public List<Team> getAllTeamsSorted(String sortField, boolean ascending) {
        if (sortField == null || sortField.isEmpty()) {
            sortField = "team_id";  // Default sort field
        }

        Sort sort = Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        return teamsRepository.findAll(sort);
    }

    public Team createTeam(Team team) {
        team.setCreatedAt(LocalDateTime.now());
        team.setUpdatedAt(LocalDateTime.now());
        return teamsRepository.save(team);
    }

}
