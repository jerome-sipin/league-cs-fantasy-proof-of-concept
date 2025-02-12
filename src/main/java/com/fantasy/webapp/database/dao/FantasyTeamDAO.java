package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.FantasyTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface FantasyTeamDAO extends JpaRepository<FantasyTeam, Long> {

    FantasyTeam findById(Integer id);

    List<FantasyTeam> findByTeamNameIgnoreCase(String teamName);

    @Query(value = "select  * from teams_fantasy order by points desc", nativeQuery = true)
    List<FantasyTeam> sortFantasyTeamsByPoints();

    FantasyTeam findByUserId(Integer userId);

    FantasyTeam findByTeamName(String teamName);

    // Retrieve total cost of team
    @Query(value = "select sum(p.cost)" +
                "from players_fantasy as pf " +
                "left join players p on p.id = pf.player_id " +
                "left join teams_fantasy tf on tf.id = fantasy_team_id " +
                "where tf.id = :teamId ", nativeQuery = true)
    Integer getTeamTotalCost(Integer teamId);

    // Retrieve current amount of players in team.
    @Query(value = "select count(player_id) from players_fantasy where fantasy_team_id = :teamId", nativeQuery = true)
    Integer getTeamPlayerCount(Integer teamId);

    // Update fantasy team budget
    @Query(value = "update teams_fantasy set budget = :price where user_id = :userId", nativeQuery = true)
    void updateBudget(Integer userId, Integer price);


    // TODO - Get Team Total Points
    // This doesn't really make complete sense for a "final" product. Say, for example, a user starts playing
    // this fantasy game like, halfway through the season. Why would they get points based on performances of players
    // that have already happened?

    @Query(value = "select sum(points) from players p " +
            "inner join players_fantasy pf on pf.player_id = p.id " +
            "where pf.fantasy_team_id = :teamId", nativeQuery = true)
    Integer getTeamTotalPoints(Integer teamId);

}
