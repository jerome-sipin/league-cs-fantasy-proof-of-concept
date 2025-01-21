package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.dao.PlayerDAO;
import com.fantasy.webapp.database.dao.RealTeamDAO;
import com.fantasy.webapp.database.entity.Player;
import com.fantasy.webapp.database.entity.RealTeam;
import com.fantasy.webapp.form.CreatePlayerFormBean;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    // TODO - sortPlayersByCost method in the DAO. Like, I want to see a table of all players
    //  below the search bar before the user has even typed anything in.
    //  this could probably be done just by removing the JSTL if statement.
    // Note - The second parameter in .addObject() MUST match the id and names of the form search box in the jsp
    // That's why it was returning null.
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
        // TODO - Remove this
        //System.out.println(response);
        log.debug(response.toString());
        return response;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/player/create")
    public ModelAndView create() {
        ModelAndView response = new ModelAndView();

        response.setViewName("player/create");

        // TODO - likely need to add object for current existing teams to put into a dropdown in the page
        // Get all  teams
        List<RealTeam> teams = realTeamDAO.findAll();
        log.debug(teams.toString());
        response.addObject("teamsKey", teams);

        return response;
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/player/createSubmit")
    public ModelAndView createPlayerSubmit(@Valid CreatePlayerFormBean form, BindingResult bindingResult) {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            response.setViewName("player/create");
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);
            log.debug(bindingResult.toString());
        } else{
            Player player = new Player();

            player.setPlayerName(form.getPlayerName());
            player.setTeamActualId(form.getActualTeamId());
            player.setCost(form.getCost());
            player.setImageUrl(form.getPlayerImageUrl());
            playerDAO.save(player);

            response.setViewName("redirect:/player/search");

        }

        return response;
    }




}
