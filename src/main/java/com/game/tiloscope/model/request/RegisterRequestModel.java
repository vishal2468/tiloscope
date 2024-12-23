package com.game.tiloscope.model.request;

import lombok.Data;

@Data
public class RegisterRequestModel {
    String name;
    String email;
    String password;
    String photoUrl;
    String description;
}
