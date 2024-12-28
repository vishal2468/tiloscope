package com.game.tiloscope.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.game.tiloscope.model.entity.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player,String>{

    @Query("select p from Player p WHERE p.email = :email")
    Player findByEmail(String email);

    Optional<Player> findByUserName(String username);
}
