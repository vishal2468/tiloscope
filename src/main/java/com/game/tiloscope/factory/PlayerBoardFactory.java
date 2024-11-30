package com.game.tiloscope.factory;

import org.springframework.stereotype.Component;

import com.game.tiloscope.model.entity.Board;
import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.model.entity.PlayerBoard;

@Component
public class PlayerBoardFactory {
    public PlayerBoard getPlayerBoard(Player player , Board board){
        PlayerBoard playerBoard = new PlayerBoard();
        playerBoard.setPlayer(player);
        playerBoard.setBoard(board);
        return playerBoard;
    }
}
