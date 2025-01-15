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


    // TODO implement these methods
    // TODO Following two methods generate the form to create a team and submit the team respectively.
    // This method will make the query to get real team names, the players that play for these teams, and their cost
    // I feel like the 1,000,000 budget should be implemented with Javascript. If too time consuming to implement,
    // then we can save this for a later time after this has been completed for class.
    @GetMapping("/fantasy_team/create")
    public ModelAndView createFantasyTeam(){
        ModelAndView response = new ModelAndView();
        return response;
    }

    // This method takes the inputted data and appends the new team to the database.
    @PostMapping("/fantasy_team/createTeam")
    public ModelAndView createTeamSubmit(@Valid CreateFantasyTeamFormBean form, BindingResult bindingResult) throws Exception{
        ModelAndView response = new ModelAndView();
        return response;
    }


}
