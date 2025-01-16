package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.dao.PlayerDAO;
import com.fantasy.webapp.database.dao.RealTeamDAO;
import com.fantasy.webapp.database.entity.Player;
import com.fantasy.webapp.database.entity.RealTeam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
