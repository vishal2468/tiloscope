package com.game.tiloscope.model.request;

import lombok.Data;

@Data
public class CreateThemeRequest {
    private String name;
    private String description;
    private String themeUrl;

}
