package com.game.tiloscope.model.entity;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Player {
    @Id
    UUID id;
    String name;
    String description;

    @ManyToMany
    Set<Tile> tiles;

}
