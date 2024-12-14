package com.game.tiloscope.model.entity;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PlayerBoardSquare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "player_board_id" , nullable = false)
    PlayerBoard playerBoard;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "square_id")
    Square square;

    @ManyToMany(mappedBy = "playerBoardSquares")
    @JsonManagedReference
    Set<Tile> tiles;
}
