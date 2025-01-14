package com.game.tiloscope.factory;

import org.springframework.stereotype.Component;

import com.game.tiloscope.model.entity.Board;
import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.model.entity.PlayerBoard;
import com.game.tiloscope.model.entity.PlayerBoardSquare;
import com.game.tiloscope.model.entity.Square;
import com.game.tiloscope.model.entity.Theme;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerBoardFactory {
    public PlayerBoard createPlayerBoard(Player player , Board board , Theme theme){
        PlayerBoard playerBoard = new PlayerBoard();
        playerBoard.setPlayer(player);
        playerBoard.setBoard(board);
        playerBoard.setTheme(theme);
        playerBoard.setPlayerBoardSquares(createPlayerBoardSquares(playerBoard, board));
        playerBoard.setLastUpdated(LocalDateTime.now());
        return playerBoard;
    }

    public List<PlayerBoardSquare> createPlayerBoardSquares(PlayerBoard playerBoard , Board board){
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
