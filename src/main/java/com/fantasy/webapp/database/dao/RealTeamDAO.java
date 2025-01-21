package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.RealTeam;
import com.fantasy.webapp.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RealTeamDAO extends JpaRepository<RealTeam, Long> {

    List<RealTeam> findByTeamNameContainingIgnoreCase(String teamName);

    RealTeam findById(Integer id);

    List<RealTeam> findTeamNamesById(Integer id);

    @Query("select rt from RealTeam  rt")
    List<RealTeam> findAllTeams();

    @Query("select rt from RealTeam rt where rt.teamName = :realTeamName")
    RealTeam findByTeamNameExact(String realTeamName);
}
