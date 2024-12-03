package com.game.tiloscope.controller;

import org.springframework.web.bind.annotation.RestController;

import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class PlayerController {

    PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player")
    public Player createPlayer() {
        return playerService.creatPlayer();
    }

}
