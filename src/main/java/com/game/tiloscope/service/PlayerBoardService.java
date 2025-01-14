package com.game.tiloscope.service;

import com.game.tiloscope.factory.PlayerBoardFactory;
import com.game.tiloscope.model.entity.*;
import com.game.tiloscope.model.request.CreatePlayerBoardRequest;
import com.game.tiloscope.repository.PlayerBoardRepository;
import com.game.tiloscope.repository.PlayerBoardSquareRepository;
import com.game.tiloscope.repository.ThemeRepository;
import com.game.tiloscope.repository.TileRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class PlayerBoardService {

    private final PlayerBoardRepository playerBoardRepository;
    private final PlayerService playerService;
    private final BoardService boardService;
    private final PlayerBoardFactory playerBoardFactory;
    private final PlayerBoardSquareRepository playerBoardSquareRepository;
    private final TileRepository tileRepository;
    private final ThemeService themeService;

    public PlayerBoardService(PlayerBoardRepository playerBoardRepository, PlayerService playerService,
                              BoardService boardService, PlayerBoardFactory playerBoardFactory, 
                              PlayerBoardSquareRepository playerBoardSquareRepository,
                              ThemeService themeService
            , TileRepository tileRepository) {
        this.playerBoardRepository = playerBoardRepository;
        this.playerService = playerService;
        this.boardService = boardService;
        this.playerBoardFactory = playerBoardFactory;
        this.playerBoardSquareRepository = playerBoardSquareRepository;
        this.tileRepository = tileRepository;
        this.themeService = themeService;
    }

    public PlayerBoard createPlayerBoard(String email, CreatePlayerBoardRequest playerBoardRequest) {
        Player p = playerService.findByEmail(email).orElseThrow();
        Board b = boardService.findById(UUID.fromString(playerBoardRequest.getBoardId()));
        Theme t = themeService.getCurrentTheme();
        return playerBoardRepository.save(playerBoardFactory.createPlayerBoard(p, b, t));
    }

    public PlayerBoard getPlayerBoard(UUID playerBoardId) {
        return playerBoardRepository.findById(playerBoardId).orElseThrow();
    }

    public PlayerBoardSquare updatePlayerBoardSquare(String playerBoardSquareId, List<String> tileIds) {
        PlayerBoardSquare playerBoardSquare = playerBoardSquareRepository.findById(UUID.fromString(playerBoardSquareId)).orElseThrow();
        playerBoardSquare.getTiles().forEach(tile -> {
            tile.getPlayerBoardSquares().remove(playerBoardSquare);
            tileRepository.save(tile);
        });  // Clear existing tiles before adding new ones
        Set<Tile> tiles = new HashSet<>();
        tileRepository.findAllById(tileIds.stream().map(UUID::fromString).toList()).forEach(tiles::add);
        tiles.forEach(tile -> tile.getPlayerBoardSquares().add(playerBoardSquare));
        tileRepository.saveAll(tiles);
        playerBoardSquare.setTiles(tiles);
        playerBoardSquareRepository.save(playerBoardSquare);
        return playerBoardSquare;
    }
}
