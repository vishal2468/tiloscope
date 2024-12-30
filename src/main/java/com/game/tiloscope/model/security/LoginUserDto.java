package com.game.tiloscope.model.security;

import lombok.Data;
@Data
public class LoginUserDto {
    private String email;

    private String password;
}