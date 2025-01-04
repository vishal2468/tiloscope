package com.game.tiloscope.model.security;

import com.game.tiloscope.model.entity.Player;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private Player player;
    private String token;
    private long expiresIn;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }
}
