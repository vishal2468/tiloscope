package com.game.tiloscope.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.game.tiloscope.factory.PlayerBoardFactory;
import com.game.tiloscope.model.entity.Board;
import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.model.entity.PlayerBoard;
import com.game.tiloscope.model.entity.PlayerBoardSquare;
import com.game.tiloscope.model.entity.PlayerBoardSquareUpdateRequest;
import com.game.tiloscope.model.entity.PlayerBoardUpdateRequest;
import com.game.tiloscope.model.entity.Tile;
import com.game.tiloscope.repository.PlayerBoardRepository;
import com.game.tiloscope.repository.PlayerBoardSquareRepository;
import com.game.tiloscope.repository.TileRepository;

@Service
public class PlayerBoardService {

    private PlayerBoardRepository playerBoardRepository;
    private PlayerService playerService;
    private BoardService boardService;
    private PlayerBoardFactory playerBoardFactory;
    private PlayerBoardSquareRepository playerBoardSquareRepository;
    private TileRepository tileRepository;


    public PlayerBoardService(PlayerBoardRepository playerBoardRepository, PlayerService playerService,
            BoardService boardService , PlayerBoardFactory playerBoardFactory , PlayerBoardSquareRepository playerBoardSquareRepository
            , TileRepository tileRepository) {
        this.playerBoardRepository = playerBoardRepository;
        this.playerService = playerService;
        this.boardService = boardService;
        this.playerBoardFactory = playerBoardFactory;
        this.playerBoardSquareRepository = playerBoardSquareRepository;
        this.tileRepository = tileRepository;

    }

    public PlayerBoard createPlayerBoard(UUID playerId, UUID boardId) {
        Player p = playerService.findById(playerId);
        Board b = boardService.findById(boardId);
        return playerBoardRepository.save(playerBoardFactory.createPlayerBoard(p, b));
    }

    public void updatePlayerBoard(PlayerBoardUpdateRequest playerBoardUpdateRequest) {
        // update all the player board square
        List<PlayerBoardSquare> playerBoardSquares = new ArrayList<>();
        for (PlayerBoardSquareUpdateRequest square : playerBoardUpdateRequest.getPlayerBoardSquareUpdateRequests()) {
            PlayerBoardSquare playerBoardSquare = playerBoardSquareRepository.findById(UUID.fromString(square.getPlayerBoardSquareId())).orElseThrow();
            Set<Tile> tiles = new HashSet<>();
            tileRepository.findAllById(square.getTileIds().stream().map(UUID::fromString).toList()).forEach(tiles::add);
            playerBoardSquare.setTiles(tiles);
            playerBoardSquares.add(playerBoardSquare);
        }
        playerBoardSquareRepository.saveAll(playerBoardSquares);
    }

    public PlayerBoard getPlayerBoard(UUID playerBoardId) {
        return playerBoardRepository.findById(playerBoardId).orElseThrow();
    }
}
