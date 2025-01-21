package com.example.sportsapp.service;

import com.example.sportsapp.model.Team;
import com.example.sportsapp.model.User;
import com.example.sportsapp.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamsService {

    private final TeamsRepository teamsRepository;

    @Autowired
    public TeamsService(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    public List<Team> getAllTeams(){
        return teamsRepository.findAll();
    }

    public Optional<Team> findByName(String name) {
        return teamsRepository.findByName(name);
    }

    public void save(Team teams) {
        teamsRepository.save(teams);
    }

    public String registerTeam(Team team) {
        Optional<Team> existingTeam = findByName(team.getName());
        if (existingTeam.isPresent()) {
            return "Name already taken";
        }
        save(team); // Save the user after password encoding
        return "Team registered successfully";
    }

}
