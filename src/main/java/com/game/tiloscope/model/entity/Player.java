package com.game.tiloscope.model.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Player {

    @Id
    private String userName;

    private String name;
    private String email;

    @JsonIgnore
    private String password;

    private String roles = "ROLE_USER";

    private String photoUrl;
    private String description;

    @ManyToMany(mappedBy="players")
    @JsonIgnore
    private Set<Tile> tiles;

    private boolean isActive;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Player other = (Player) obj;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }
    
}
