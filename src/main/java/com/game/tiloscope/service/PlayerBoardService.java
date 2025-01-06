package com.game.tiloscope.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.game.tiloscope.model.entity.*;
import org.springframework.stereotype.Service;

import com.game.tiloscope.factory.PlayerBoardFactory;
import com.game.tiloscope.repository.PlayerBoardRepository;
import com.game.tiloscope.repository.PlayerBoardSquareRepository;
import com.game.tiloscope.repository.TileRepository;

@Service
public class PlayerBoardService {

    private final PlayerBoardRepository playerBoardRepository;
    private final PlayerService playerService;
    private final BoardService boardService;
    private final PlayerBoardFactory playerBoardFactory;
    private final PlayerBoardSquareRepository playerBoardSquareRepository;
    private final TileRepository tileRepository;


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

    public PlayerBoard createPlayerBoard(String email, UUID boardId) {
        Player p = playerService.findByEmail(email).orElseThrow();
        Board b = boardService.findById(boardId);
        return playerBoardRepository.save(playerBoardFactory.createPlayerBoard(p, b));
    }

    public PlayerBoard getPlayerBoard(UUID playerBoardId) {
        return playerBoardRepository.findById(playerBoardId).orElseThrow();
    }

    public PlayerBoardSquare updatePlayerBoardSquare(String playerBoardSquareId, List<String> tileIds) {
        PlayerBoardSquare playerBoardSquare = playerBoardSquareRepository.findById(UUID.fromString(playerBoardSquareId)).orElseThrow();
        Set<Tile> tiles = new HashSet<>();
        tileRepository.findAllById(tileIds.stream().map(UUID::fromString).toList()).forEach(tiles::add);
        tiles.forEach(tile->tile.getPlayerBoardSquares().add(playerBoardSquare));
        tileRepository.saveAll(tiles);
        playerBoardSquare.setTiles(tiles);
        playerBoardSquareRepository.save(playerBoardSquare);
        return playerBoardSquare;
    }
}
