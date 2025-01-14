package com.game.tiloscope.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.game.tiloscope.model.entity.Theme;
import com.game.tiloscope.model.request.CreateThemeRequest;
import com.game.tiloscope.repository.ThemeRepository;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@CrossOrigin
@RequestMapping("/theme")
public class ThemeController {

    private ThemeRepository themeRepository;

    private Theme currentTheme;

    private Date currentDate;

    public ThemeController(ThemeRepository themeRepository){
        this.themeRepository = themeRepository;
    }

    @PostMapping
    public Theme createTheme(@RequestBody CreateThemeRequest createThemeRequest){
        Theme theme = new Theme();
        theme.setName(createThemeRequest.getName());
        theme.setDescription(createThemeRequest.getDescription());
        theme.setThemeUrl(createThemeRequest.getThemeUrl());
        return themeRepository.save(theme);
    }

    public List<Theme> getAllTheme(){
        return themeRepository.findAll();
    }

    @GetMapping("/today")
    public Theme getTodayTheme(){
        if(currentDate == null || currentDate.before(new Date(new Date().getTime() - (1000 * 60 * 60 * 24)))){
            currentDate = new Date();
            List<Theme> themes = themeRepository.findAll();
            Collections.shuffle(themes);
            currentTheme = themes.get(0);
        }
        return currentTheme;
    }

}
