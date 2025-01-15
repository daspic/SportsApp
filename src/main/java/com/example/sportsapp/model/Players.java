package com.example.sportsapp.model;

import jakarta.persistence.*;

@Entity
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn (name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn (name = "team_id", nullable = false)
    private Team team_id;

    private String player_stats;
}
