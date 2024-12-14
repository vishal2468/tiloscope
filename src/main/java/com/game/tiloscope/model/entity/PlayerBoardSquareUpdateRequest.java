package com.game.tiloscope.model.entity;

import java.util.List;

import lombok.Data;

@Data
public class PlayerBoardSquareUpdateRequest {

    String playerBoardSquareId;
    List<String> tileIds;
}
