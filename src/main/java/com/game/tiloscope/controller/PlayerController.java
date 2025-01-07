package com.game.tiloscope.controller;

import com.game.tiloscope.configuration.LoggedInUser;
import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.model.request.RegisterPlayerRequest;
import com.game.tiloscope.model.security.LoginResponse;
import com.game.tiloscope.model.security.LoginUserDto;
import com.game.tiloscope.model.security.MyUserDetails;
import com.game.tiloscope.service.AuthenticationService;
import com.game.tiloscope.service.JwtService;
import com.game.tiloscope.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@CrossOrigin
public class PlayerController {

    private final PlayerService playerService;

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public PlayerController(PlayerService playerService,
                            AuthenticationService authenticationService,
                            JwtService jwtService) {
        this.playerService = playerService;
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;

    }

    @PostMapping("/auth/player")
    public Player createPlayer(@RequestBody RegisterPlayerRequest registerRequestModel) {
        Optional<Player> player = playerService.findByEmail(registerRequestModel.getEmail());
        if (player.isPresent()) {
            throw new RuntimeException("Player already exists with this email");
        }
        return playerService.createPlayer(registerRequestModel);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Player authenticatedUser = authenticationService.authenticate(loginUserDto);
        if (authenticatedUser.getTiles() != null) {
            authenticatedUser = playerService.assignTiles(authenticatedUser);
        }
        String jwtToken = jwtService.generateToken(new MyUserDetails(authenticatedUser));
        LoginResponse loginResponse = LoginResponse.builder()
                .player(authenticatedUser)
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/player/tile/{tileId}")
    public Player addTile(@LoggedInUser MyUserDetails userDetails, @PathVariable String tileId) {
        return playerService.addTile(userDetails.getUser().getEmail(), UUID.fromString(tileId));
    }

    /*
     * Get most upvote players
     */
    @GetMapping("player/leaderboard")
    public List<Object[]> leaderboard() {
        return playerService.findPlayersByCumulativeLikes();
    }

}
