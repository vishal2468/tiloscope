package com.game.tiloscope.service;

import java.util.UUID;

import com.game.tiloscope.model.request.RegisterRequestModel;
import com.game.tiloscope.utility.HashMakerUtility;
import org.springframework.stereotype.Service;

import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.model.entity.Tile;
import com.game.tiloscope.repository.PlayerRepository;
import com.game.tiloscope.repository.TileRepository;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TileRepository tileRepository;
    private final HashMakerUtility hashMakerUtility;

    public PlayerService(PlayerRepository playerRepository , TileRepository tileRepository, HashMakerUtility hashMakerUtility){
        this.playerRepository = playerRepository;
        this.tileRepository = tileRepository;
        this.hashMakerUtility = hashMakerUtility;
    }

    public Player findById(UUID playerId) {
        return playerRepository.findById(playerId).orElseThrow();
    }

    public Player findByEmail(String email) {
        return playerRepository.findByEmail(email);
    }

    public Player createPlayer(RegisterRequestModel registerRequestModel) {
        Player player = new Player();
        player.setName(registerRequestModel.getName());
        player.setEmail(registerRequestModel.getEmail());
        player.setPassword(this.hashMakerUtility.hashPasswordBase64(registerRequestModel.getPassword()));
        player.setPhotoUrl(registerRequestModel.getPhotoUrl());
        player.setDescription(registerRequestModel.getDescription());
        return playerRepository.save(player);
    }

    public Player addTile(UUID playerId , UUID tileId) {
        Player player = playerRepository.findById(playerId).orElseThrow();
        Tile tile = tileRepository.findById(tileId).orElseThrow();
        player.getTiles().add(tile);
        tile.getPlayers().add(player);
        tileRepository.save(tile);
        return playerRepository.save(player);
    }
}
