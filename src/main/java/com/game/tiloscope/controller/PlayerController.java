package com.game.tiloscope.controller;

import org.springframework.web.bind.annotation.RestController;

import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.service.PlayerService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class PlayerController {

    PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/player/{name}")
    public Player createPlayer(@PathVariable String name) {
        return playerService.createPlayer();
    }

}
