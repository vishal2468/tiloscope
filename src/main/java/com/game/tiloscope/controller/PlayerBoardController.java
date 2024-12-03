package com.game.tiloscope.controller;

import com.game.tiloscope.model.entity.PlayerBoard;
import com.game.tiloscope.service.PlayerBoardService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class PlayerBoardController {

    private PlayerBoardService playerBoardService;

    public PlayerBoardController( PlayerBoardService playerBoardService) {
        this.playerBoardService = playerBoardService;
    }

    @GetMapping("/playerboard/{boardId}/{playerId}")
    public PlayerBoard createBoard(@PathVariable String boardId , @PathVariable String playerId){
        return playerBoardService.createPlayerBoard(UUID.fromString(playerId), UUID.fromString(boardId));
    }
}
