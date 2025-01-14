package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.FantasyPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FantasyPlayerDAO extends JpaRepository<FantasyPlayer, Long> {

    FantasyPlayer findPlayerById(Integer playerId);

    FantasyPlayer findFantasyTeamById(Integer fantasyTeamId);

}
