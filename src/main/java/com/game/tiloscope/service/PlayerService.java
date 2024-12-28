package com.game.tiloscope.service;

import java.util.UUID;

import com.game.tiloscope.model.request.RegisterPlayerRequest;
import com.game.tiloscope.utility.HashMakerUtility;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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

    public Player findByUserName(String userName) {
        return playerRepository.findByUserName(userName).orElseThrow();
    }

    public Player findByEmail(String email) {
        return playerRepository.findByEmail(email);
    }

    public Player createPlayer(RegisterPlayerRequest registerPlayerRequest) {
        Player player = new Player();
        player.setUserName(registerPlayerRequest.getUserName());
        player.setEmail(registerPlayerRequest.getEmail());
        player.setPassword(NoOpPasswordEncoder.getInstance().encode(registerPlayerRequest.getPassword()));
        player.setPhotoUrl(registerPlayerRequest.getPhotoUrl());
        player.setDescription(registerPlayerRequest.getDescription());
        return playerRepository.save(player);
    }

    public Player addTile(String userName , UUID tileId) {
        Player player = playerRepository.findByUserName(userName).orElseThrow();
        Tile tile = tileRepository.findById(tileId).orElseThrow();
        player.getTiles().add(tile);
        tile.getPlayers().add(player);
        tileRepository.save(tile);
        return playerRepository.save(player);
    }
}
