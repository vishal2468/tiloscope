package com.game.tiloscope.controller;

import com.game.tiloscope.configuration.LoggedInUser;
import com.game.tiloscope.model.entity.*;
import com.game.tiloscope.model.security.MyUserDetails;
import com.game.tiloscope.repository.PlayerBoardRepository;
import com.game.tiloscope.repository.PlayerRepository;
import com.game.tiloscope.service.PlayerBoardService;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/playerboard")
@CrossOrigin
public class PlayerBoardController {

    private final PlayerBoardService playerBoardService;
    private final PlayerBoardRepository playerBoardRepository;
    private final PlayerRepository playerRepository;

    public PlayerBoardController(PlayerBoardService playerBoardService , PlayerBoardRepository playerBoardRepository , PlayerRepository playerRepository) {
        this.playerBoardService = playerBoardService;
        this.playerBoardRepository = playerBoardRepository;
        this.playerRepository = playerRepository;
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
    public PlayerBoardSquare updatePlayerBoardSquare(@RequestBody PlayerBoardSquareUpdateRequest squareUpdate ){
        return playerBoardService.updatePlayerBoardSquare(squareUpdate.getPlayerBoardSquareId() , squareUpdate.getTileIds());
    }

    /*
     * Update a player board
     */
    @PutMapping
    public PlayerBoard updatePlayerBoard(@RequestBody PlayerBoardUpdateRequest boardUpdate ){
        boardUpdate.getPlayerBoardSquareUpdateRequests().forEach(us -> playerBoardService.updatePlayerBoardSquare(us.getPlayerBoardSquareId(),us.getTileIds()));
        return playerBoardRepository.findById(UUID.fromString(boardUpdate.getPlayerBoardId())).orElseThrow();
    }

    /*
     * Upvote a player board
     */
    @PutMapping("/upvote/{playerBoardId}")
    public PlayerBoard upvote(@LoggedInUser MyUserDetails myUserDetails , @PathVariable String playerBoardId) {
        PlayerBoard playerBoard = playerBoardRepository.findById(UUID.fromString(playerBoardId)).orElseThrow();
        Set<Player> playersLiked = playerBoard.getLiked();
        Player player = myUserDetails.getUser();
        playersLiked.add(player);
        player.getLikedPlayerBoards().add(playerBoard);
        playerRepository.save(player);
        return playerBoardRepository.save(playerBoard);
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
