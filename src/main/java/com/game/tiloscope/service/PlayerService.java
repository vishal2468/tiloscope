package com.game.tiloscope.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.model.entity.Tile;
import com.game.tiloscope.repository.PlayerRepository;
import com.game.tiloscope.repository.TileRepository;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private TileRepository tileRepository;

    public PlayerService(PlayerRepository playerRepository , TileRepository tileRepository){
        this.playerRepository = playerRepository;
        this.tileRepository = tileRepository;
    }

    public Player findById(UUID playerId) {
        return playerRepository.findById(playerId).orElseThrow();
    }

    public Player createPlayer(String name) {
        Player player = new Player();
        player.setName(name);
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
