package com.game.tiloscope.controller;

import com.game.tiloscope.model.entity.PlayerBoard;
import com.game.tiloscope.repository.PlayerBoardRepository;
import com.game.tiloscope.service.PlayerBoardService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/shareView")
@CrossOrigin
public class PlayerBoardShareController {

    private final PlayerBoardRepository playerBoardRepository;

    public PlayerBoardShareController(PlayerBoardService playerBoardService, PlayerBoardRepository playerBoardRepository) {
        this.playerBoardRepository = playerBoardRepository;
    }

    /*
     * Share a player board using the playerBoardId board template
     */
    @GetMapping("/{playerBoardId}")
    public PlayerBoard shareBoard(@PathVariable String playerBoardId) {
        return playerBoardRepository.findById(UUID.fromString(playerBoardId)).orElseThrow();
    }

}
