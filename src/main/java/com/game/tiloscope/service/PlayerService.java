package com.game.tiloscope.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.repository.PlayerRepository;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public Player findById(UUID playerId) {
        return playerRepository.findById(playerId).orElseThrow();
    }

    public Player creatPlayer() {
        Player player = new Player();
        player.setName("vishal");
        return playerRepository.save(player);
    }

}
