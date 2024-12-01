package com.game.tiloscope.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.game.tiloscope.model.entity.Board;
import com.game.tiloscope.model.entity.Square;

@Component
public class BoardFactory {
   public Board getBoard(int row , int col) {
       Board board = new Board();
       board.setRows(row);
       board.setCols(col);
       List<Square> squares = new ArrayList<>();
       for(int i=0;i<row;i++){
        for(int j=0;j<col;j++){
            Square square = new Square();
            square.setRow(i);
            square.setCol(j);
            square.setBoard(board);
            squares.add(square);
        }
       }
       board.setSquares(squares);
       return board;
   }
}

