package com.game.tiloscope.model.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Board {
    @Id
    UUID id;
    String name;
    String description;
    int rows;
    int coloumns;
    int level;
}
