package com.game.tiloscope.model.entity;

import lombok.Data;

import java.util.List;

@Data
public class PlayerBoardUpdateRequest {
    String playerBoardId;
    List<PlayerBoardSquareUpdateRequest> playerBoardSquareUpdateRequests;
}
