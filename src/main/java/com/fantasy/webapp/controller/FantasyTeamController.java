package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.dao.FantasyPlayerDAO;
import com.fantasy.webapp.database.dao.FantasyTeamDAO;
import com.fantasy.webapp.database.dao.UserDAO;
import com.fantasy.webapp.database.entity.FantasyTeam;
import com.fantasy.webapp.database.entity.User;
import com.fantasy.webapp.form.CreateFantasyTeamFormBean;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    private UserDAO userDAO;

    // TODO! How to make it such that "User" shows up as the username,
    // TODO! and not the user id?
    // TODO! maybe just use a native query here so we can join with the user table and get the username from there?
    // TODO!  also make it such this query searches for "LIKE", not exact.
    // TODO! OR!!!! Or maybe do it as a separate query and then add it to the model??? Would that work???
    // TODO! Also, consider changing the DAO method to something like findByTeamNameContainingIgnoreCase
    @GetMapping("/fantasy_team/search")
    public ModelAndView search(@RequestParam(required = false) String teamName){
        ModelAndView response = new ModelAndView();

        response.setViewName("fantasy_team/search");

        response.addObject("search", teamName);

        if (teamName != null){
            List<FantasyTeam> team = fantasyTeamDAO.findByTeamNameIgnoreCase(teamName);
            // Get userid from above list, then query db for said user
            // TODO! Again, ask Eric. Is this secure???
            for (FantasyTeam t : team){
                User user = userDAO.findById(t.getUserId());
                response.addObject("usersKey", user);
            }
            response.addObject("teamsKey", team);
        }
        return response;
    }



    // TODO - shows user a user-made fantasy team
    @GetMapping("/fantasy_team/view")
    public ModelAndView viewFantasyTeam(){
        ModelAndView response = new ModelAndView();
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
    @GetMapping("/fantasy_team/create")
    public ModelAndView createFantasyTeam(){
        ModelAndView response = new ModelAndView();
        // chheck if team belongs to user

        return response;

    }

    // This method takes the inputted data and appends the new team to the database.
    @PostMapping("/fantasy_team/createTeam")
    public ModelAndView createTeamSubmit(@Valid CreateFantasyTeamFormBean form, BindingResult bindingResult) throws Exception{
        ModelAndView response = new ModelAndView();
        return response;
    }



}
