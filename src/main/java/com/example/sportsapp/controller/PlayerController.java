package com.example.sportsapp.controller;

import com.example.sportsapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService playerService;
}
