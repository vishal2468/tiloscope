package com.game.tiloscope.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.game.tiloscope.model.entity.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player,UUID>{
    Optional<Player> findByEmail(String username);

    @Query("SELECT p, SUM(COALESCE(size(pb.liked), 0)) AS totalLikes " +
            "FROM Player p " +
            "JOIN p.likedPlayerBoards pb " +
            "GROUP BY p " +
            "ORDER BY totalLikes DESC")
    List<Object[]> findPlayersByCumulativeLikes();
}
