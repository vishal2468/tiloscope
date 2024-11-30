package com.game.tiloscope.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.game.tiloscope.factory.PlayerBoardFactory;
import com.game.tiloscope.model.entity.Board;
import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.repository.PlayerBoardRepository;

@Service
public class PlayerBoardService {

    private PlayerBoardRepository playerBoardRepository;
    private PlayerService playerService;
    private BoardService boardService;
    private PlayerBoardFactory playerBoardFactory;

    public PlayerBoardService(PlayerBoardRepository playerBoardRepository, PlayerService playerService,
            BoardService boardService , PlayerBoardFactory playerBoardFactory) {
        this.playerBoardRepository = playerBoardRepository;
        this.playerService = playerService;
        this.boardService = boardService;
        this.playerBoardFactory = playerBoardFactory;
    }

    public void createPlayerBoard(UUID playerId, UUID boardId) {
        Player p = playerService.findById(playerId);
        Board b = boardService.findById(boardId);
        playerBoardFactory.getPlayerBoard(p, b);
        playerBoardRepository.save(playerBoardFactory.getPlayerBoard(p, b));
    }
}
