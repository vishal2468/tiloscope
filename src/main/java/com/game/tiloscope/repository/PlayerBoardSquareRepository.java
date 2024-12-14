package com.game.tiloscope.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.game.tiloscope.model.entity.PlayerBoardSquare;

@Repository
public interface PlayerBoardSquareRepository extends CrudRepository<PlayerBoardSquare,UUID>{
    
}
