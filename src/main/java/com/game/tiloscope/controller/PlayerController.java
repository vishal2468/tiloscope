package com.game.tiloscope.controller;

import com.game.tiloscope.model.request.RegisterPlayerRequest;
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

    @PostMapping("/player")
    public Player createPlayer(@RequestBody RegisterPlayerRequest registerRequestModel) {
        Player player = playerService.findByEmail(registerRequestModel.getEmail());
        if(null != player) {
            throw new RuntimeException("Player already exists with this email");
        }
        return playerService.createPlayer(registerRequestModel);
    }

    @PostMapping("/player/{userName}/tile/{tileId}")
    public Player addTile(@PathVariable String userName, @PathVariable String tileId) {
        return playerService.addTile(userName , UUID.fromString(tileId));
    }

}
