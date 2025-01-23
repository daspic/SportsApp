package com.example.sportsapp.model;

import com.example.sportsapp.model.Team;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "PLAYERS")
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "players_seq")
    @SequenceGenerator(name = "players_seq", sequenceName = "players_seq", allocationSize = 1)
    private Long player_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    private String player_stats;

    // Getters and setters
    public Long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Long player_id) {
        this.player_id = player_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getPlayer_stats() {
        return player_stats;
    }

    public void setPlayer_stats(String player_stats) {
        this.player_stats = player_stats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Players)) return false;
        Players players = (Players) o;
        return Objects.equals(player_id, players.player_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player_id);
    }
}
