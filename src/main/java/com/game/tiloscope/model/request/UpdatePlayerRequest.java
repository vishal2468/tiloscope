package com.game.tiloscope.model.request;

import io.micrometer.common.lang.Nullable;
import lombok.Data;

@Data
public class UpdatePlayerRequest {

    private String id;
    @Nullable
    private String name;
    @Nullable
    private String password;
    @Nullable
    private String photoUrl;
    @Nullable
    private String description;
}
