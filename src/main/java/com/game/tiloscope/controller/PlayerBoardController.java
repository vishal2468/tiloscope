package com.game.tiloscope.controller;

import com.game.tiloscope.model.entity.PlayerBoard;
import com.game.tiloscope.model.entity.PlayerBoardSquare;
import com.game.tiloscope.model.entity.PlayerBoardSquareUpdateRequest;
import com.game.tiloscope.repository.PlayerBoardRepository;
import com.game.tiloscope.service.PlayerBoardService;

import jakarta.servlet.ServletException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PlayerBoardController {

    private final PlayerBoardService playerBoardService;
    private final PlayerBoardRepository playerBoardRepository;

    public PlayerBoardController( PlayerBoardService playerBoardService , PlayerBoardRepository playerBoardRepository) {
        this.playerBoardService = playerBoardService;
        this.playerBoardRepository = playerBoardRepository;
    }

    @GetMapping("/playerboard/{playerBoardId}")
    public PlayerBoard createBoard(@PathVariable String playerBoardId ){
        return playerBoardService.getPlayerBoard(UUID.fromString(playerBoardId));
    }

    @PostMapping("/playerboard/{playerId}/{boardId}")
    public PlayerBoard createBoard(@PathVariable String boardId , @PathVariable String playerId){
        return playerBoardService.createPlayerBoard(UUID.fromString(playerId), UUID.fromString(boardId));
    }

    @PutMapping("/playerboard/square")
    public PlayerBoardSquare updatePlayerBoard(@RequestBody PlayerBoardSquareUpdateRequest squareUpdate ){
        return playerBoardService.updatePlayerBoardSquare(squareUpdate.getPlayerBoardSquareId() , squareUpdate.getTileIds());
    }

    @ResponseBody
    @GetMapping("/playerboard")
    public List<PlayerBoard> getAllPlayerBoards(@PageableDefault(value=10, page=0) Pageable pageable) throws ServletException {
        Page<PlayerBoard> page = playerBoardRepository.findAll(pageable);
        return page.getContent();
    }

    @ResponseBody
    @PutMapping("/playerboard/upvote/{playerBoardId}")
    public PlayerBoard upvote(@PathVariable String playerBoardId) {
        PlayerBoard playerBoard = playerBoardRepository.findById(UUID.fromString(playerBoardId)).orElseThrow();
        playerBoard.setVote(playerBoard.getVote()+1);
        return playerBoardRepository.save(playerBoard);
    }

    @ResponseBody
    @GetMapping("/playerboard")
    public List<PlayerBoard> leaderboard(@PageableDefault(value=10, page=0 , sort = "vote", direction = Sort.Direction.DESC) Pageable pageable) throws ServletException {
        Page<PlayerBoard> page = playerBoardRepository.findAll(pageable);
        return page.getContent();
    }

}
