package com.game.tiloscope.controller;

import com.game.tiloscope.model.entity.PlayerBoard;
import com.game.tiloscope.model.entity.PlayerBoardSquare;
import com.game.tiloscope.model.entity.PlayerBoardSquareUpdateRequest;
import com.game.tiloscope.service.PlayerBoardService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class PlayerBoardController {

    private final PlayerBoardService playerBoardService;

    public PlayerBoardController( PlayerBoardService playerBoardService) {
        this.playerBoardService = playerBoardService;
    }

    @GetMapping("/playerboard/{playerBoardId}")
    public PlayerBoard getPlayerBoard(@PathVariable String playerBoardId ){
        return playerBoardService.getPlayerBoard(UUID.fromString(playerBoardId));
    }

    @PostMapping("/playerboard/{playerId}/{boardId}")
    public PlayerBoard createBoard(@PathVariable String boardId , @PathVariable String playerId){
        return playerBoardService.createPlayerBoard(UUID.fromString(playerId), UUID.fromString(boardId));
    }

    @PutMapping("/playerboard")
    public PlayerBoardSquare updatePlayerBoard(@RequestBody PlayerBoardSquareUpdateRequest squareUpdate ){
        return playerBoardService.updatePlayerBoardSquare(squareUpdate.getPlayerBoardSquareId() , squareUpdate.getTileIds());
    }
}
