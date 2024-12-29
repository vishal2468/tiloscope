package com.game.tiloscope.service;

import java.util.Optional;
import java.util.UUID;

import com.game.tiloscope.model.request.RegisterPlayerRequest;
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

    public PlayerService(PlayerRepository playerRepository , TileRepository tileRepository){
        this.playerRepository = playerRepository;
        this.tileRepository = tileRepository;
    }

    public Optional<Player> findByEmail(String email) {
        return playerRepository.findByEmail(email);
    }

    public Player createPlayer(RegisterPlayerRequest registerPlayerRequest) {
        Player player = new Player();
        player.setEmail(registerPlayerRequest.getEmail());
        player.setName(registerPlayerRequest.getName());
        player.setActive(true);
        player.setPassword(NoOpPasswordEncoder.getInstance().encode(registerPlayerRequest.getPassword()));
        player.setPhotoUrl(registerPlayerRequest.getPhotoUrl());
        player.setDescription(registerPlayerRequest.getDescription());
        return playerRepository.save(player);
    }

    public Player addTile(String email , UUID tileId) {
        Player player = playerRepository.findByEmail(email).orElseThrow();
        Tile tile = tileRepository.findById(tileId).orElseThrow();
        player.getTiles().add(tile);
        tile.getPlayers().add(player);
        tileRepository.save(tile);
        return playerRepository.save(player);
    }
}
