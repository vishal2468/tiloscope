package com.game.tiloscope.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.tiloscope.model.entity.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, UUID>{

}
