package com.game.tiloscope.model.request;

import lombok.Data;

@Data
public class PlayerBoardVisibilityRequest {

    private String playerBoardId;

    private boolean visible;

}
