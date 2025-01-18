package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.dao.*;
import com.fantasy.webapp.database.entity.*;
import com.fantasy.webapp.form.CreateFantasyTeamFormBean;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class FantasyTeamController {

    @Autowired
    private FantasyTeamDAO fantasyTeamDAO;

    @Autowired
    private FantasyPlayerDAO fantasyPlayerDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RealTeamDAO realTeamDAO;
    @Autowired
    private PlayerDAO playerDAO;

    @GetMapping("/fantasy_team/search")
    public ModelAndView search(@RequestParam(required = false) String teamName){
        ModelAndView response = new ModelAndView();

        response.setViewName("fantasy_team/search");

        response.addObject("search", teamName);

        if (teamName != null){
            List<FantasyTeam> team = fantasyTeamDAO.findByTeamNameIgnoreCase(teamName);
            // Get userid from above list, then query db for said user
            for (FantasyTeam t : team){
                User user = userDAO.findById(t.getUserId());
                response.addObject("usersKey", user);
            }
            response.addObject("teamsKey", team);
        }
        return response;
    }



    // Shows user-made fantasy team
    @GetMapping("/fantasy_team/view/{fantasyTeamId}")
    public ModelAndView viewFantasyTeam(@PathVariable Integer fantasyTeamId){
        ModelAndView response = new ModelAndView();

        response.setViewName("fantasy_team/view");

        List<FantasyPlayer> teamRoster = fantasyPlayerDAO.findPlayersByFantasyTeamId(fantasyTeamId);
        FantasyTeam teamInformation = fantasyTeamDAO.findById(fantasyTeamId);

        List<Player> playerInformation = new ArrayList<>();
        for (FantasyPlayer x : teamRoster){
            playerInformation.add(x.getPlayer());
        }

        response.addObject("teamInformationKey", teamInformation);
        response.addObject("playersKey", playerInformation);

        log.debug(response.toString());

        return response;
    }

    // TODO implement these methods
    // TODO Following two methods generate the form to create a team and submit the team respectively.
    // TODO Also, gate these two options behind being logged in once security is implemented.
    // This method will make the query to get real team names, the players that play for these teams, and their cost
    // I feel like the $1,000,000 budget should be implemented with Javascript. If too time consuming to implement,
    // then we can save this for a later time after this has been completed for class.
    //
    // Don't forget that you can have a max of 2 players from the same team in a fantasy team. That logic
    // could be a part of this controller method. Get the list of players from the form bean. Call the playerDAO
    // and find each player's original team, and append them to a list. If there are three or more identical team ids
    // in this list, then the team is invalid.
    //
    // Notice how in HLTV fantasy, when there are players you cannot select (too many players from a certain team
    // or not enough money), they are greyed out. I think that would probably be something done with Javascript.
    //
    // Start this by just having basic tables with one row being blank (placeholder for pictures), one row being
    // player name, and one row being their price/select button. Then mirror these choices to a table at the top that
    // has the player picture, and their name.
    // TODO - there needs to be some sort of authorization here. If the user already has a team, do not show
    // TODO - them the create button.
    @GetMapping("/fantasy_team/create")
    public ModelAndView createFantasyTeam() {
        ModelAndView response = new ModelAndView();

        response.setViewName("fantasy_team/create");

        // first order of business - run DAO queries to retrieve all real teams, their players, and the cost of said players
        List<RealTeam> realTeams = realTeamDAO.findAllTeams();
        // List<Player> players = playerDAO.findAllPlayers();

        List<Player> players = new ArrayList<>();
        for (RealTeam rt : realTeams){
            List<Player> roster = playerDAO.findPlayersByTeamActualId(rt.getId());
            players.addAll(roster);
        }

        Integer budget = 1000000;

        response.addObject("budget", budget);
        response.addObject("realTeamsKey", realTeams);
        response.addObject("playersKey", players);



//        log.debug(realTeams.toString());
//        log.debug(players.toString());
        return response;

    }

    @GetMapping("/fantasy_team/addPlayer")
    public ModelAndView createFantasyTeam(@RequestParam Integer playerId) {
        // Use the authenticated user service to look up

        ModelAndView response = new ModelAndView();
        return response;


    }

    // TODO - one step that should be taken - check if team belongs to user

    // This method takes the inputted data and appends the new team to the database.
    @PostMapping("/fantasy_team/createTeam")
    public ModelAndView createTeamSubmit(@Valid CreateFantasyTeamFormBean form, BindingResult bindingResult) throws Exception{
        ModelAndView response = new ModelAndView();
        return response;

        // listen to submit button. On submit, retrieve what is in the top-most table.
        // We need to have some way to track what  is in that top-most table that represents the user's team
        // how will that be done?
        // refer more to customer/create in examples.
    }



}
