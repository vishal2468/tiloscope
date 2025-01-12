package com.game.tiloscope.service;

import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.model.entity.Tile;
import com.game.tiloscope.model.request.RegisterPlayerRequest;
import com.game.tiloscope.model.request.UpdatePlayerRequest;
import com.game.tiloscope.repository.PlayerRepository;
import com.game.tiloscope.repository.TileRepository;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TileRepository tileRepository;

    public PlayerService(PlayerRepository playerRepository, TileRepository tileRepository) {
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

    public Player addTile(String email, UUID tileId) {
        Player player = playerRepository.findByEmail(email).orElseThrow();
        Tile tile = tileRepository.findById(tileId).orElseThrow();
        player.getTiles().add(tile);
        tile.getPlayers().add(player);
        tileRepository.save(tile);
        return playerRepository.save(player);
    }

    public Player assignTiles(Player player) {
        List<Tile> tiles = (List<Tile>) tileRepository.findAll();

        if (tiles.size() >= 10) {
            Collections.shuffle(tiles);
            List<Tile> assignedTiles = tiles.subList(0, 10);
            assignedTiles.forEach(tile -> {
                tile.getPlayers().add(player);
            });
            player.setTiles(new HashSet<>(assignedTiles));
            tileRepository.saveAll(assignedTiles);
        }

        return player;

    }

    public List<Object[]> findPlayersByCumulativeLikes() {
        return playerRepository.findPlayersByCumulativeLikes();
    }

    public Player updatePlayer(UpdatePlayerRequest updatePlayerRequest) {
        Player player = playerRepository.findById(UUID.fromString(updatePlayerRequest.getId()))
                .orElseThrow();
        if(updatePlayerRequest.getName() != null) player.setName(updatePlayerRequest.getName());
        if(updatePlayerRequest.getPassword() != null) player.setPassword(NoOpPasswordEncoder.getInstance().encode(updatePlayerRequest.getPassword()));
        if(updatePlayerRequest.getPhotoUrl() != null) player.setPhotoUrl(updatePlayerRequest.getPhotoUrl());
        if(updatePlayerRequest.getDescription() != null) player.setDescription(updatePlayerRequest.getDescription());
        return playerRepository.save(player);
    }
}
