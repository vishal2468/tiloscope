package com.game.tiloscope.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.game.tiloscope.model.entity.Tile;

@Repository
public interface TileRepository extends CrudRepository<Tile,UUID>{
    
}
