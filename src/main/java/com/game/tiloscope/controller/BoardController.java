package com.game.tiloscope.controller;

import com.game.tiloscope.factory.BoardFactory;
import com.game.tiloscope.model.entity.Board;
import com.game.tiloscope.model.request.CreateBoardRequest;
import com.game.tiloscope.repository.BoardRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@CrossOrigin
@RequestMapping("board")
public class BoardController {

    private final BoardFactory boardFactory;
    private final BoardRepository boardRepository;

    public BoardController( BoardFactory boardFactory, BoardRepository boardRepository) {
        this.boardFactory = boardFactory;
        this.boardRepository = boardRepository;
    }

    @PostMapping
    public Board createBoard(@RequestBody CreateBoardRequest board){
        return boardRepository.save(boardFactory.getBoard(board.getRows() , board.getCols()));
    }

    @GetMapping
    public ResponseEntity<List<Board>> getAllBoard(){
        return ResponseEntity.ok(boardRepository.findAll());
    }

    @GetMapping("/{boardId}")
    public Board getBoard(@PathVariable String boardId){
        return boardRepository.findById(UUID.fromString(boardId)).orElseThrow();
    }
}
