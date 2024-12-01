package com.game.tiloscope.model.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Square {
    int row;
    int col;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @JoinColumn(name = "board_id")
    @ManyToOne
    @JsonBackReference
    Board board;
}
