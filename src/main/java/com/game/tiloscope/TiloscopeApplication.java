package com.game.tiloscope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.game.tiloscope.factory.BoardFactory;

@SpringBootApplication
public class TiloscopeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiloscopeApplication.class, args);
	}

}
