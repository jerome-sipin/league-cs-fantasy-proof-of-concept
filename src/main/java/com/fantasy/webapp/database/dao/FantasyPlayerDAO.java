package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.FantasyPlayer;
import com.fantasy.webapp.database.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FantasyPlayerDAO extends JpaRepository<FantasyPlayer, Long> {

    FantasyPlayer findPlayerById(Integer playerId);

    FantasyPlayer findFantasyTeamById(Integer fantasyTeamId);

    // TODO!
    List<FantasyPlayer> findPlayersByFantasyTeamId(Integer teamId);

}
