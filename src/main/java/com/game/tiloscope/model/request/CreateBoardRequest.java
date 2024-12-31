package com.game.tiloscope.model.request;

import lombok.Data;

@Data
public class CreateBoardRequest {
    private int rows;
    private int cols;
}
