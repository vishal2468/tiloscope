package com.game.tiloscope.controller;

import com.game.tiloscope.model.request.RegisterPlayerRequest;
import com.game.tiloscope.model.security.LoginResponse;
import com.game.tiloscope.model.security.LoginUserDto;
import com.game.tiloscope.model.security.MyUserDetails;
import com.game.tiloscope.service.AuthenticationService;
import com.game.tiloscope.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.service.PlayerService;

import java.util.Optional;
import java.util.UUID;


@RestController
@CrossOrigin
public class PlayerController {

    private final PlayerService playerService;

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public PlayerController(PlayerService playerService ,
                            AuthenticationService authenticationService ,
                            JwtService jwtService ) {
        this.playerService = playerService;
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;

    }

    @PostMapping("/auth/player")
    public Player createPlayer(@RequestBody RegisterPlayerRequest registerRequestModel) {
        Optional<Player> player = playerService.findByEmail(registerRequestModel.getEmail());
        if(player.isPresent()) {
            throw new RuntimeException("Player already exists with this email");
        }
        return playerService.createPlayer(registerRequestModel);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Player authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(new MyUserDetails(authenticatedUser));

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/player/{email}/tile/{tileId}")
    public Player addTile(@PathVariable String email, @PathVariable String tileId) {
        return playerService.addTile(email , UUID.fromString(tileId));
    }

}
