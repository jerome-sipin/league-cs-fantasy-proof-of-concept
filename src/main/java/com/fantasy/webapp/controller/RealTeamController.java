package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.dao.PlayerDAO;
import com.fantasy.webapp.database.dao.RealTeamDAO;
import com.fantasy.webapp.database.entity.Player;
import com.fantasy.webapp.database.entity.RealTeam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class RealTeamController {

    @Autowired
    private RealTeamDAO realTeamDAO;

    @Autowired
    private PlayerDAO playerDAO;

    // TODO - maybe this doesn't even have to  be a search page? Does it still cont as
    // TODO - dynamic if it just shows a table of teams that are populated through the backend?
    // TODO - even though the database is kind of dynamic?
    // TODO - this is a good question for Eric.
    @GetMapping("/real_team/search")
    public ModelAndView search(@RequestParam(required = false) String teamName){
        ModelAndView response = new ModelAndView();

        response.setViewName("real_team/search");

        response.addObject("search", teamName);

        if(teamName != null ){
            List<RealTeam> teams = realTeamDAO.findByTeamNameContainingIgnoreCase(teamName);
            for (RealTeam realTeam : teams) {
                List<Player> players = playerDAO.findPlayersByTeamActualId(realTeam.getId());
                response.addObject("playersKey", players);
            }
            response.addObject("teamsKey", teams);
        }
        return response;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("real_team/create")
    public ModelAndView createRealTeam(){
        ModelAndView response = new ModelAndView();

        response.setViewName("real_team/create");

        return response;
    }

}
