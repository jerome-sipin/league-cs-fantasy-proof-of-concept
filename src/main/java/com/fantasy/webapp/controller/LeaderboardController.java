package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.dao.FantasyTeamDAO;
import com.fantasy.webapp.database.dao.PlayerDAO;
import com.fantasy.webapp.database.dao.RealTeamDAO;
import com.fantasy.webapp.database.dao.UserDAO;
import com.fantasy.webapp.database.entity.FantasyTeam;
import com.fantasy.webapp.database.entity.Player;
import com.fantasy.webapp.database.entity.RealTeam;
import com.fantasy.webapp.database.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class LeaderboardController {

    // TODO - This should really only have like two views. One is for team leaderboard, the other is for
    // TODO - player leaderboard. Have there be two buttons at the top. This controls the view.
    // TODO - The data is then displayed guarded by an if statement in the page.

    // TODO - Also, it would be a good idea to ask about this. How would the pages be structured?

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private FantasyTeamDAO fantasyTeamDAO;

    @Autowired
    private RealTeamDAO realTeamDAO;

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/leaderboard")
    public ModelAndView leaderboard() {
        ModelAndView response = new ModelAndView();

        response.setViewName("/leaderboard");

        List<FantasyTeam> teams = fantasyTeamDAO.sortFantasyTeamsByPoints();
        List<Player> players = playerDAO.sortPlayersByScore();

        response.addObject("playersKey", players);

        // Important!!!
        // https://stackoverflow.com/questions/4142631/is-it-possible-to-iterate-two-items-simultaneously-using-foreach-in-jstl
        // JSTL did not want to recognize realTeamsKey as something I could do .teamName on. Refer to
        // leaderboard.jsp and look at the varStatus="status" in the result table.
        List<RealTeam> realTeams = new ArrayList<>();
        for (Player player : players) {
            RealTeam playerActualTeam = realTeamDAO.findById(player.getTeamActualId());
            realTeams.add(playerActualTeam);
        }

        List<User> users = new ArrayList<>();
        for (FantasyTeam team : teams) {
            User fantasyUser = userDAO.findById(team.getUserId());
            users.add(fantasyUser);
        }

        response.addObject("realTeamsKey", realTeams);

        response.addObject("usersKey", users);

        response.addObject("teamsKey", teams);

        log.debug(response.toString());

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
