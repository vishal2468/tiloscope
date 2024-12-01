package com.game.tiloscope.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.game.tiloscope.factory.BoardFactory;
import com.game.tiloscope.model.entity.Board;
import com.game.tiloscope.repository.BoardRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BoardController {

    @Autowired
    BoardFactory boardFactory;

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/board")
    public Board createBoard(){
        return boardRepository.save(boardFactory.getBoard(2, 2));
    }
}
