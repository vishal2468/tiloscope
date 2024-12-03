package com.game.tiloscope.controller;

import com.game.tiloscope.factory.BoardFactory;
import com.game.tiloscope.model.entity.Board;
import com.game.tiloscope.repository.BoardRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class BoardController {

    private BoardFactory boardFactory;
    private BoardRepository boardRepository;

    public BoardController( BoardFactory boardFactory, BoardRepository boardRepository) {
        this.boardFactory = boardFactory;
        this.boardRepository = boardRepository;
    }

    @GetMapping("/board")
    public Board createBoard(){
        return boardRepository.save(boardFactory.getBoard(2, 2));
    }
}
