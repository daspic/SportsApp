package com.example.sportsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Objects;

@Entity(name = "PLAYERS")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "players_seq")
    @SequenceGenerator(name = "players_seq", sequenceName = "players_seq", allocationSize = 1)
    @Column(name = "player_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    @JsonIgnore // Prevent circular reference
    private Team team;

    @Column(name="player_stats")
    private String playerStats;

    // Getters and setters
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
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
        // If the player is already associated with the team, do nothing
        if (this.team != null && this.team != team) {
            this.team.removePlayer(this);  // Remove from the old team
        }

        this.team = team;  // Set the new team

        if (team != null) {
            team.addPlayer(this);  // Add to the new team, but do not trigger setTeam again
        }
    }

    public String getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(String playerStats) {
        this.playerStats = playerStats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
