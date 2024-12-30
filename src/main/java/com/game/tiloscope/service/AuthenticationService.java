package com.game.tiloscope.service;

import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.model.security.LoginUserDto;
import com.game.tiloscope.repository.PlayerRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final PlayerRepository userRepository;

//     private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            PlayerRepository userRepository,
            AuthenticationManager authenticationManager
        //     PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        // this.passwordEncoder = passwordEncoder;
    }

    public Player authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}