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
public class PlayerController {

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private RealTeamDAO realTeamDAO;

    // TODO - Is there a way to have a default search result? That would be a good use case for the
    // TODO - sortPlayersByCost method in the DAO.
    // TODO - ??? why is it returning search = null???
    @GetMapping("/player/search")
    public ModelAndView search(@RequestParam(required = false) String playerName) {
        ModelAndView response = new ModelAndView();

        response.setViewName("player/search");

        response.addObject("search", playerName);

        if (playerName != null) {
            List<Player> players = playerDAO.findByPlayerNameContainingIgnoreCase(playerName);
            for (Player p : players) {
                RealTeam team = realTeamDAO.findById(p.getTeamActualId());
                response.addObject("teamsKey", team);
            }
            response.addObject("playersKey", players);
        }
        System.out.println(response);
        return response;
    }



}
