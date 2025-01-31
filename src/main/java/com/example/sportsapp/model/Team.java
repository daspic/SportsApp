package com.example.sportsapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "TEAMS")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teams_seq")
    @SequenceGenerator(name = "teams_seq", sequenceName = "teams_seq", allocationSize = 1)
    @Column(name = "team_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "team")
    private List<Player> players = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Add player to the team
    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this); // Maintain bidirectional consistency
    }

    // Remove player from the team
    public void removePlayer(Player player) {
        players.remove(player);
        player.setTeam(null); // Maintain bidirectional consistency
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Player> getPlayers() {
        return players;
    }

    protected void setPlayers(List<Player> players) {
        this.players.clear();
        this.players.addAll(players);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Objects.equals(description, team.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
