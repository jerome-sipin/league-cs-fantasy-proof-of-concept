package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.FantasyTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface FantasyTeamDAO extends JpaRepository<FantasyTeam, Long> {

    FantasyTeam findById(Integer id);

    FantasyTeam findByTeamName(String teamName);

    @Query(value = "select  * from teams_fantasy order by points desc", nativeQuery = true)
    List<Map<String,Object>> sortTeamsByPoints();

}
