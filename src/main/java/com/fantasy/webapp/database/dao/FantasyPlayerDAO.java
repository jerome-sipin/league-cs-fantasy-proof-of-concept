package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.FantasyPlayer;
import com.fantasy.webapp.database.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FantasyPlayerDAO extends JpaRepository<FantasyPlayer, Long> {

    FantasyPlayer findPlayerById(Integer playerId);

    FantasyPlayer findFantasyTeamById(Integer fantasyTeamId);

    List<FantasyPlayer> findPlayersByFantasyTeamId(Integer teamId);

    @Query(value = "select count(p.team_actual_id) " +
                    "from players_fantasy pf " +
                    "left join players p on p.id = pf.player_id " +
                    "where pf.fantasy_team_id = :fantasyTeamId " +
                    "group by p.team_actual_id " +
                    "order by p.team_actual_id", nativeQuery = true)
    List<Integer> getFantasyTeamActualTeamCountsByFantasyTeamId(Integer fantasyTeamId);

    // Drop player from fantasy team
    // For future reference
    // https://stackoverflow.com/questions/25821579/transactionrequiredexception-executing-an-update-delete-query
    // helped solve this. at least when deleting, @Transactional annotation needed in DAO
    @Modifying
    @Transactional
    @Query(value = "delete from players_fantasy where player_id = :playerId and fantasy_team_id = :fantasyTeamId", nativeQuery = true)
    void dropPlayer(Integer fantasyTeamId, Integer playerId);

}
