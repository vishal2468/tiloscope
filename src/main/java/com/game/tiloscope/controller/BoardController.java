package com.game.tiloscope.controller;

import com.game.tiloscope.factory.BoardFactory;
import com.game.tiloscope.model.entity.Board;
import com.game.tiloscope.repository.BoardRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BoardController {

    private BoardFactory boardFactory;
    private BoardRepository boardRepository;

    public BoardController( BoardFactory boardFactory, BoardRepository boardRepository) {
        this.boardFactory = boardFactory;
        this.boardRepository = boardRepository;
    }

    @PostMapping("/board")
    public Board createBoard(){
        return boardRepository.save(boardFactory.getBoard(2, 2));
    }
}
