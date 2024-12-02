package com.game.tiloscope.model.entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    List<PlayerBoardSquare> playerBoardSquare;
}
