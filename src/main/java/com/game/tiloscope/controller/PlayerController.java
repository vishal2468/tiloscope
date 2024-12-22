package com.game.tiloscope.controller;

import com.game.tiloscope.model.request.RegisterRequestModel;
import org.springframework.web.bind.annotation.*;

import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.service.PlayerService;

import java.util.UUID;


@RestController
@CrossOrigin
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/createPlayer")
    public Player createPlayer(@RequestBody RegisterRequestModel registerRequestModel) {
        Player player = playerService.findByEmail(registerRequestModel.getEmail());
        if(null != player) {
            throw new RuntimeException("Player already exists with this email");
        }
        return playerService.createPlayer(registerRequestModel);
    }

    @PostMapping("/player/{playerId}/tile/{tileId}")
    public Player addTile(@PathVariable String playerId, @PathVariable String tileId) {
        return playerService.addTile(UUID.fromString(playerId) , UUID.fromString(tileId));
    }

}
