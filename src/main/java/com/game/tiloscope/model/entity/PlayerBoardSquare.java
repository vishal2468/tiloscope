package com.game.tiloscope.model.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class PlayerBoardSquare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "player_board_id")
    PlayerBoard playerBoard;

    @ManyToOne
    @JoinColumn(name = "square_id")
    Square square;

    @ManyToMany
    List<Tile> tiles;
}
