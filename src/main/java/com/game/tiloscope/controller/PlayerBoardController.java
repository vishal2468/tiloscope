package com.game.tiloscope.controller;

import com.game.tiloscope.model.entity.PlayerBoard;
import com.game.tiloscope.model.entity.PlayerBoardSquare;
import com.game.tiloscope.model.entity.PlayerBoardSquareUpdateRequest;
import com.game.tiloscope.repository.PlayerBoardRepository;
import com.game.tiloscope.service.PlayerBoardService;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class PlayerBoardController {

    private final PlayerBoardService playerBoardService;
    private final PlayerBoardRepository playerBoardRepository;

    public PlayerBoardController( PlayerBoardService playerBoardService , PlayerBoardRepository playerBoardRepository) {
        this.playerBoardService = playerBoardService;
        this.playerBoardRepository = playerBoardRepository;
    }

    @GetMapping("/playerboard/{playerBoardId}")
    public PlayerBoard getPlayerBoard(@PathVariable String playerBoardId ){
        return playerBoardService.getPlayerBoard(UUID.fromString(playerBoardId));
    }

    @PostMapping("/playerboard/{email}/{boardId}")
    public PlayerBoard createBoard(@PathVariable String boardId , @PathVariable String email){
        return playerBoardService.createPlayerBoard(email, UUID.fromString(boardId));
    }

    @PutMapping("/playerboard")
    public PlayerBoardSquare updatePlayerBoard(@RequestBody PlayerBoardSquareUpdateRequest squareUpdate ){
        return playerBoardService.updatePlayerBoardSquare(squareUpdate.getPlayerBoardSquareId() , squareUpdate.getTileIds());
    }

    @GetMapping("/playerboard")
    public List<PlayerBoard> getAllPlayerBoards(@PageableDefault(value=10, page=0) Pageable pageable){
        Page<PlayerBoard> page = playerBoardRepository.findAll(pageable);
        return page.getContent();
    }

    @PutMapping("/playerboard/upvote/{playerBoardId}")
    public PlayerBoard upvote(@PathVariable String playerBoardId) {
        PlayerBoard playerBoard = playerBoardRepository.findById(UUID.fromString(playerBoardId)).orElseThrow();
        playerBoard.setVote(playerBoard.getVote()+1);
        return playerBoardRepository.save(playerBoard);
    }

    @GetMapping("/playerboard/leaderboard")
    public List<PlayerBoard> leaderboard(@PageableDefault(value=10, page=0 , sort = "vote", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PlayerBoard> page = playerBoardRepository.findAll(pageable);
        return page.getContent();
    }

}
