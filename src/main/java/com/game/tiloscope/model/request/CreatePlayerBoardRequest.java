package com.game.tiloscope.model.request;

import lombok.Data;

@Data
public class CreatePlayerBoardRequest {
    String boardId;
    String themeId;
}
