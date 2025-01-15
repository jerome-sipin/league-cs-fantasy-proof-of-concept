package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PlayerDAO extends JpaRepository<Player, Long> {

    List<Player> findById(Integer id);

    List<Player> findByPlayerNameContainingIgnoreCase(String playerName);

    @Query(value = "select  * from players order by points desc", nativeQuery = true)
    List<Map<String,Object>> sortPlayersByScore();

    @Query(value = "select  * from players order by cost desc", nativeQuery = true)
    List<Map<String,Object>> sortPlayersByCost();

    @Query(value = "select p.player_name, tr.team_name" +
                "from players p" +
                "left join teams_real tr" +
                "on p.team_actual = tr.id" +
                "where team_name = :teamName", nativeQuery = true)
    List<Map<String,Object>> findPlayersByTeamName(String teamName);

}
