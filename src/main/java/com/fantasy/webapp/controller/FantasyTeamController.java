package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.dao.FantasyPlayerDAO;
import com.fantasy.webapp.database.dao.FantasyTeamDAO;
import com.fantasy.webapp.database.entity.FantasyTeam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class FantasyTeamController {

    @Autowired
    private FantasyTeamDAO fantasyTeamDAO;

    @Autowired
    FantasyPlayerDAO fantasyPlayerDAO;

    @GetMapping("/fantasy_team/search")
    public ModelAndView search(@RequestParam(required = false) String teamName){
        ModelAndView response = new ModelAndView();

        response.setViewName("fantasy_team/search");

        if (teamName != null){
            List<FantasyTeam> teams = fantasyTeamDAO.findByTeamNameIgnoreCase(teamName);
            response.addObject("teamsKey", teams);
        }

        return response;
    }

}
