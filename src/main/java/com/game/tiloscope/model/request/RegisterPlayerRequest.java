package com.game.tiloscope.model.request;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class RegisterPlayerRequest {
    String userName;
    String email;
    String password;
    String photoUrl;
    String description;
}
