package com.game.tiloscope.controller;

import com.game.tiloscope.configuration.LoggedInUser;
import com.game.tiloscope.model.entity.PlayerBoard;
import com.game.tiloscope.model.entity.PlayerBoardSquare;
import com.game.tiloscope.model.entity.PlayerBoardSquareUpdateRequest;
import com.game.tiloscope.model.security.MyUserDetails;
import com.game.tiloscope.repository.PlayerBoardRepository;
import com.game.tiloscope.service.PlayerBoardService;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/playerboard")
@CrossOrigin
public class PlayerBoardController {

    private final PlayerBoardService playerBoardService;
    private final PlayerBoardRepository playerBoardRepository;

    public PlayerBoardController( PlayerBoardService playerBoardService , PlayerBoardRepository playerBoardRepository) {
        this.playerBoardService = playerBoardService;
        this.playerBoardRepository = playerBoardRepository;
    }

    /*
     * Create a player board using the boardId board template
     */
    @PostMapping("/{boardId}")
    public PlayerBoard createBoard(@PathVariable String boardId , @LoggedInUser MyUserDetails userDetails){
        return playerBoardService.createPlayerBoard(userDetails.getUsername(), UUID.fromString(boardId));
    }

    /*
     * Get all player boards for a user
     */
    @GetMapping("/my")
    public List<PlayerBoard> getMyPlayerBoards(@LoggedInUser MyUserDetails userDetails){
        return playerBoardRepository.findByPlayerId(userDetails.getUser().getId());
    }

    /*
     * Get the player board with id
     */
    @GetMapping("{playerBoardId}")
    public PlayerBoard getPlayerBoard(@PathVariable String playerBoardId ){
        return playerBoardService.getPlayerBoard(UUID.fromString(playerBoardId));
    }

    /*
     * Update a player board square
     */
    @PutMapping("square")
    public PlayerBoardSquare updatePlayerBoard(@RequestBody PlayerBoardSquareUpdateRequest squareUpdate ){
        return playerBoardService.updatePlayerBoardSquare(squareUpdate.getPlayerBoardSquareId() , squareUpdate.getTileIds());
    }

    /*
     * Upvote a player board
     */
    @PutMapping("/upvote/{playerBoardId}")
    public PlayerBoard upvote(@PathVariable String playerBoardId) {
        PlayerBoard playerBoard = playerBoardRepository.findById(UUID.fromString(playerBoardId)).orElseThrow();
        playerBoard.setVote(playerBoard.getVote()+1);
        return playerBoardRepository.save(playerBoard);
    }

    /*
     * Get most upvoted boards
     */
    @GetMapping("/leaderboard")
    public List<PlayerBoard> leaderboard(@PageableDefault(value=10, page=0 , sort = "vote", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PlayerBoard> page = playerBoardRepository.findAll(pageable);
        return page.getContent();
    }

    /*
     * Get all player boards in the game
     */
    @GetMapping
    public ResponseEntity<List<PlayerBoard>> getAllPlayerBoards(@PageableDefault(value=10, page=0) Pageable pageable){
        Page<PlayerBoard> page = playerBoardRepository.findAll(pageable);
        return ResponseEntity.ok(page.getContent());
    }

}
