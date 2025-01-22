package com.example.sportsapp.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity(name = "PLAYERS")
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "players_seq")
    @SequenceGenerator(name = "players_seq", sequenceName = "players_seq", allocationSize = 1)
    private Long player_id;

    @ManyToOne(optional = false)
    @JoinColumn (name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne(optional = false)
    @JoinColumn (name = "team_id", nullable = false)
    private Team team_id;

    private String player_stats;

    // Getters and setters

    public Long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Long player_id) {
        this.player_id = player_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Team getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Team team_id) {
        this.team_id = team_id;
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
