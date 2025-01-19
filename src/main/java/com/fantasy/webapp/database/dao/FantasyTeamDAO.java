package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.FantasyTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface FantasyTeamDAO extends JpaRepository<FantasyTeam, Long> {

    FantasyTeam findById(Integer id);


//    @Query("select ft from FantasyTeam ft where ft.teamName = :teamName")
//    List<FantasyTeam> findByTeamName(String teamName);

    List<FantasyTeam> findByTeamNameIgnoreCase(String teamName);

    @Query(value = "select  * from teams_fantasy order by points desc", nativeQuery = true)
    //List<Map<String,Object>> sortTeamsByPoints();
    List<FantasyTeam> sortFantasyTeamsByPoints();

    FantasyTeam findByUserId(Integer userId);

    FantasyTeam findByTeamName(String teamName);

}
