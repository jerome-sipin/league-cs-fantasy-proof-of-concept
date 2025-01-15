package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.dao.FantasyPlayerDAO;
import com.fantasy.webapp.database.dao.FantasyTeamDAO;
import com.fantasy.webapp.database.entity.FantasyTeam;
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

    // TODO! How to make it such that "User" shows up as the username,
    // TODO! and not the user id?
    @GetMapping("/fantasy_team/search")
    public ModelAndView search(@RequestParam(required = false) String teamName){
        ModelAndView response = new ModelAndView();

        response.setViewName("fantasy_team/search");

        response.addObject("search", teamName);

        if (teamName != null){
            List<FantasyTeam> team = fantasyTeamDAO.findByTeamNameIgnoreCase(teamName);
            response.addObject("teamsKey", team);
        }
        System.out.println(response);
        return response;
    }



    // TODO implement these methods
    // TODO Following two methods generate the form to create a team and submit the team respectively.
    @GetMapping("/fantasy_team/create")
    public ModelAndView createFantasyTeam(){
        ModelAndView response = new ModelAndView();
        return response;
    }

    @PostMapping("/fantasy_team/createTeam")
    public ModelAndView createTeamSubmit(@Valid CreateFantasyTeamFormBean form, BindingResult bindingResult) throws Exception{
        ModelAndView response = new ModelAndView();
        return response;
    }


}
