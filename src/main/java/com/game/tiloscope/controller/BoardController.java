package com.game.tiloscope.controller;

import com.game.tiloscope.factory.BoardFactory;
import com.game.tiloscope.model.entity.Board;
import com.game.tiloscope.repository.BoardRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class BoardController {

    private final BoardFactory boardFactory;
    private final BoardRepository boardRepository;

    public BoardController( BoardFactory boardFactory, BoardRepository boardRepository) {
        this.boardFactory = boardFactory;
        this.boardRepository = boardRepository;
    }

    @PostMapping("/board/{rows}/{cols}")
    public Board createBoard(@PathVariable String rows , @PathVariable String cols ){
        return boardRepository.save(boardFactory.getBoard(Integer.parseInt(rows) , Integer.parseInt(cols)));
    }
}
