package com.game.tiloscope.model.entity;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Tile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String name;
    String description;
    String html;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
      })
    @JsonBackReference
    @JoinTable(name = "tile_join_player", joinColumns = @JoinColumn(name = "tile_id"), inverseJoinColumns = @JoinColumn(name = "player_id"))
    Set<Player> players;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
      })
    @JsonBackReference
    @JoinTable(name = "tile_join_player_board_square", joinColumns = @JoinColumn(name = "tile_id"), inverseJoinColumns = @JoinColumn(name = "player_board_square_id"))
    Set<PlayerBoardSquare> playerBoardSquares;

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Tile other = (Tile) obj;
      if (id == null) {
        if (other.id != null)
          return false;
      } else if (!id.equals(other.id))
        return false;
      return true;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
    }
    
}
