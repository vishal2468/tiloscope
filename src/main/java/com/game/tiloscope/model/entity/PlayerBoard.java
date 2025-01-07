package com.game.tiloscope.model.entity;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PlayerBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id")
    Board board;

    @OneToMany(mappedBy = "playerBoard" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonManagedReference
    List<PlayerBoardSquare> playerBoardSquares;

    @ManyToMany(mappedBy="likedPlayerBoards" , fetch = FetchType.EAGER)
    Set<Player> liked;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerBoard that = (PlayerBoard) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
