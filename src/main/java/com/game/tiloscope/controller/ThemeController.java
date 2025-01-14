package com.game.tiloscope.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.game.tiloscope.model.entity.Theme;
import com.game.tiloscope.model.request.CreateThemeRequest;
import com.game.tiloscope.repository.ThemeRepository;
import com.game.tiloscope.service.ThemeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin
@RequestMapping("/theme")
public class ThemeController {

    private ThemeRepository themeRepository;

    private ThemeService themeServivce;

    public ThemeController(ThemeRepository themeRepository , ThemeService themeService){
        this.themeRepository = themeRepository;
        this.themeServivce = themeService;
    }

    @PostMapping
    public Theme createTheme(@RequestBody CreateThemeRequest createThemeRequest){
        Theme theme = new Theme();
        theme.setName(createThemeRequest.getName());
        theme.setDescription(createThemeRequest.getDescription());
        theme.setThemeUrl(createThemeRequest.getThemeUrl());
        return themeRepository.save(theme);
    }

    @GetMapping
    public List<Theme> getAllTheme(){
        return themeRepository.findAll();
    }

    @GetMapping("/today")
    public Theme getTodayTheme(){
        return themeServivce.getCurrentTheme();
    }

}
