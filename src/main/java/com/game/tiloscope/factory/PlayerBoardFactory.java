package com.game.tiloscope.factory;

import org.springframework.stereotype.Component;

import com.game.tiloscope.model.entity.Board;
import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.model.entity.PlayerBoard;
import com.game.tiloscope.model.entity.PlayerBoardSquare;
import com.game.tiloscope.model.entity.Square;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerBoardFactory {
    public PlayerBoard createPlayerBoard(Player player , Board board){
        PlayerBoard playerBoard = new PlayerBoard();
        playerBoard.setPlayer(player);
        playerBoard.setBoard(board);
        playerBoard.setPlayerBoardSquares(createPlayerBoardSquares(playerBoard, board, player));
        return playerBoard;
    }

    public List<PlayerBoardSquare> createPlayerBoardSquares(PlayerBoard playerBoard , Board board , Player player){
        List<Square> squares = board.getSquares();
        List<PlayerBoardSquare> playerBoardSquares = new ArrayList<>();
        for(Square square : squares){
            PlayerBoardSquare playerBoardSquare = new PlayerBoardSquare();
            playerBoardSquare.setSquare(square);
            playerBoardSquare.setPlayerBoard(playerBoard);
            playerBoardSquares.add(playerBoardSquare);
        }
        return playerBoardSquares;
    }
}
