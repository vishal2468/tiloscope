package com.game.tiloscope.model.entity;

import lombok.Data;

@Data
public class PlayerBoardUpdateRequest {
    String playerBoardId;
    PlayerBoardSquareUpdateRequest playerBoardSquareUpdateRequest;
}
