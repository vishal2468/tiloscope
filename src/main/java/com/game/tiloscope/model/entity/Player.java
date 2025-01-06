package com.game.tiloscope.model.entity;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String email;

    @JsonIgnore
    private String password;

    private String roles = "ROLE_USER";

    private String photoUrl;
    private String description;

    @ManyToMany(mappedBy="players")
    private Set<Tile> tiles;

    private boolean isActive;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinTable(name = "player_join_liked_player_board", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "player_board_id"))
    Set<PlayerBoard> likedPlayerBoards;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
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
