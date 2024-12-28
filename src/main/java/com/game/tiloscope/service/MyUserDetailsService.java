package com.game.tiloscope.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.game.tiloscope.model.entity.Player;
import com.game.tiloscope.model.security.MyUserDetails;
import com.game.tiloscope.repository.PlayerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    PlayerRepository userRepository;

    public MyUserDetailsService(PlayerRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Player> user=userRepository.findByUserName(username);
        user.orElseThrow(()->new UsernameNotFoundException("not found : "+username));
        return user.map(MyUserDetails::new).get();
    }
    
}
