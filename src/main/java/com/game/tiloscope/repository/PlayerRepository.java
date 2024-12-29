package com.game.tiloscope.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.game.tiloscope.model.entity.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player,UUID>{
    Optional<Player> findByEmail(String username);
}
