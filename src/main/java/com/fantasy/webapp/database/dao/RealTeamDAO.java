package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.RealTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RealTeamDAO extends JpaRepository<RealTeam, Long> {

    List<RealTeam> findByTeamNameContainingIgnoreCase(String teamName);

    RealTeam findById(Integer id);

}
