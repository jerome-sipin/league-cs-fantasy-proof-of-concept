package com.fantasy.webapp.database.dao;

import com.fantasy.webapp.database.entity.RealTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealTeamDAO extends JpaRepository<RealTeam, Long> {

    RealTeam findByName(String name);

    RealTeam findById(Integer id);

}
