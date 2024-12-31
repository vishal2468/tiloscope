package com.game.tiloscope.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.tiloscope.model.entity.Board;

@Repository
public interface BoardRepository  extends JpaRepository<Board , UUID> {
}

