package com.game.tiloscope.model.entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    
    String name;
    String description;
    int rows;
    int cols;
    int level;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Square> squares;
}
