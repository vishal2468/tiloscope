package com.game.tiloscope.repository;

import com.game.tiloscope.model.entity.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoardRepository extends CrudRepository<Board, UUID> {
}

