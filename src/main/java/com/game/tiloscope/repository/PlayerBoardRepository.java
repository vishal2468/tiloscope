package com.game.tiloscope.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.tiloscope.model.entity.PlayerBoard;

@Repository
public interface PlayerBoardRepository extends JpaRepository<PlayerBoard , UUID> {

    List<PlayerBoard> findByPlayerId(UUID id);

    Page<PlayerBoard> findByVisibleTrue(Pageable pageable);
}
