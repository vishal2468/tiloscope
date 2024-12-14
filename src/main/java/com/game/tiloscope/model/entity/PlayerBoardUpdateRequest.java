package com.game.tiloscope.model.entity;

import java.util.List;

import lombok.Data;

@Data
public class PlayerBoardUpdateRequest {
    String playerBoardId;
    List<PlayerBoardSquareUpdateRequest> playerBoardSquareUpdateRequests;
}
