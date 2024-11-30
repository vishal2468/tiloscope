package com.game.tiloscope.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.game.tiloscope.model.entity.Player;

public interface PlayerRepository extends CrudRepository<Player,UUID>{
    
}
