package com.game.tiloscope.model.entity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Tile {
    @Id
    UUID id;
    String name;
    String description;
    @ManyToMany
    Set<Player> players;
    @ManyToMany
    List<PlayerBoardSquare> playerBoardSquares;
}
