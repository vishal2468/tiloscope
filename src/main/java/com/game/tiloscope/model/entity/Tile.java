package com.game.tiloscope.model.entity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Tile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String name;
    String description;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
      })
    @JoinTable(name = "tile_join_player", joinColumns = @JoinColumn(name = "tile_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
    Set<Player> players;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
      })
    @JoinTable(name = "tile_join_player_board_square", joinColumns = @JoinColumn(name = "tile_id"), inverseJoinColumns = @JoinColumn(name = "player_board_square_id"))
    List<PlayerBoardSquare> playerBoardSquares;
}
