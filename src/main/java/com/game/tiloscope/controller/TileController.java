package com.game.tiloscope.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.game.tiloscope.model.entity.Tile;
import com.game.tiloscope.model.entity.TileCreateRequest;
import com.game.tiloscope.repository.TileRepository;

@RestController
@CrossOrigin
public class TileController {

    private final TileRepository tileRepository;
    
    public TileController(TileRepository tileRepository) {
        this.tileRepository = tileRepository;
    }


    @PostMapping("/tile")
    public Tile createTile(@RequestBody TileCreateRequest tileRequest ){
        Tile tile = new Tile();
        tile.setName(tileRequest.getName());
        tile.setDescription(tileRequest.getDescription());
        tile.setHtml(tileRequest.getHtml());
        tileRepository.save(tile);
        return tile;
        
    }
}
