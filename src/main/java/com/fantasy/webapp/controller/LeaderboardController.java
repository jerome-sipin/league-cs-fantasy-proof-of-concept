package com.fantasy.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LeaderboardController {

    // TODO - This should really only have like two views. One is for team leaderboard, the other is for
    // TODO - player leaderboard. Have there be two buttons at the top. This controls the view.
    // TODO - The data is then displayed guarded by an if statement in the page.

    // TODO - Also, it would be a good idea to ask about this. How would the pages be structured?


    @GetMapping("/leaderboard")
    public ModelAndView leaderboard() {
        ModelAndView response = new ModelAndView();

        response.setViewName("/leaderboard");



        return response;
    }

    // TODO - There are two buttons. Clicking on either will lead you to the corresponding view.
    // I'm pretty sure the in-class example of create customer has a view change.

    @GetMapping("/leaderboard/players")
    public ModelAndView topPlayers() {
        ModelAndView response = new ModelAndView();

        response.setViewName("/leaderboard/players");

        // add object called "playersSelected".

        // if playersSelected = true...

        return response;
    }

    @GetMapping("/leaderboard/teams")
    public ModelAndView topTeams(){
        ModelAndView response = new ModelAndView();

        response.setViewName("/leaderboard/teams");

        // add object called "teamsSelected".

        // if teamsSelected = true...

        return response;

    }

}
