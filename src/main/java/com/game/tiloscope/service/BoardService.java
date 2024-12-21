package com.game.tiloscope.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.game.tiloscope.model.entity.Board;
import com.game.tiloscope.repository.BoardRepository;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board findById(UUID boardId) {
        return boardRepository.findById(boardId).orElseThrow();
    }

}
