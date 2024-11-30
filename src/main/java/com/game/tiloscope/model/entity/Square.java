package com.game.tiloscope.model.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Square {
    int row;
    int col;
    @Id
    UUID id;
}
