package com.game.tiloscope.model.request;

import lombok.Data;

@Data
public class RegisterPlayerRequest {
    String email;
    String name;
    String password;
    String photoUrl;
    String description;
}
