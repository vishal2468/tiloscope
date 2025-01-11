package com.game.tiloscope.repository;

import com.game.tiloscope.model.entity.Player;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends CrudRepository<Player, UUID> {
    Optional<Player> findByEmail(String username);

    @NativeQuery("SELECT p.name, p.email, p.description, p.photo_url ,COALESCE(pb_likes.cont, 0) AS totalLikes " +
            "FROM Player p " +
            "LEFT JOIN (" +
            "select pb.player_id, count(*) as cont from player_join_liked_player_board pjlpb " +
            "left join player_board pb on pjlpb.player_board_id = pb.id " +
            "group by pb.player_id" +
            ") pb_likes ON p.id = pb_likes.player_id " +
            "ORDER BY totalLikes desc")
    List<Object[]> findPlayersByCumulativeLikes();
}
