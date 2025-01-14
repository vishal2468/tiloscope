package com.game.tiloscope.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.game.tiloscope.model.entity.Theme;
import com.game.tiloscope.repository.ThemeRepository;

@Service
public class ThemeService {

    private Theme currentTheme;

    private Date currentDate;

    private final ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository){

        this.themeRepository = themeRepository;
    }

    public Theme getCurrentTheme() {
        if(currentDate == null || currentDate.before(new Date(new Date().getTime() - (1000 * 60 * 60 * 24)))){
            currentDate = new Date();
            List<Theme> themes = themeRepository.findAll();
            Collections.shuffle(themes);
            currentTheme = themes.get(0);
        }
        return currentTheme;
    }
    
}
