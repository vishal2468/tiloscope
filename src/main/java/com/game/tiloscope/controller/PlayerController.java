package com.game.tiloscope.controller;

import com.game.tiloscope.model.request.RegisterPlayerRequest;
import org.springframework.web.bind.annotation.*;

import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.service.PlayerService;

import java.util.Optional;
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
        Optional<Player> player = playerService.findByEmail(registerRequestModel.getEmail());
        if(player.isPresent()) {
            throw new RuntimeException("Player already exists with this email");
        }
        return playerService.createPlayer(registerRequestModel);
    }

    @PostMapping("/player/{email}/tile/{tileId}")
    public Player addTile(@PathVariable String email, @PathVariable String tileId) {
        return playerService.addTile(email , UUID.fromString(tileId));
    }

}
