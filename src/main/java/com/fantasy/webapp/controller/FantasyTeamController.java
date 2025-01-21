package com.fantasy.webapp.controller;

import com.fantasy.webapp.database.dao.*;
import com.fantasy.webapp.database.entity.*;
import com.fantasy.webapp.form.CreateFantasyTeamFormBean;
import com.fantasy.webapp.security.AuthenticatedUserService;
import jakarta.servlet.http.HttpSession;
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
import java.util.Objects;

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

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @GetMapping("/fantasy_team/search")
    public ModelAndView search(@RequestParam(required = false) String teamName) {
        ModelAndView response = new ModelAndView();

        response.setViewName("fantasy_team/search");

        response.addObject("search", teamName);

        if (teamName != null) {
            List<FantasyTeam> team = fantasyTeamDAO.findByTeamNameIgnoreCase(teamName);
            // Get userid from above list, then query db for said user
            for (FantasyTeam t : team) {
                User user = userDAO.findById(t.getUserId());
                response.addObject("usersKey", user);
            }
            response.addObject("teamsKey", team);
        }
        return response;
    }


    // Shows user-made fantasy team
    @GetMapping("/fantasy_team/view/{fantasyTeamId}")
    public ModelAndView viewFantasyTeam(@PathVariable Integer fantasyTeamId) {
        ModelAndView response = new ModelAndView();

        response.setViewName("fantasy_team/view");

        List<FantasyPlayer> teamRoster = fantasyPlayerDAO.findPlayersByFantasyTeamId(fantasyTeamId);
        FantasyTeam teamInformation = fantasyTeamDAO.findById(fantasyTeamId);

        List<Player> playerInformation = new ArrayList<>();
        for (FantasyPlayer x : teamRoster) {
            playerInformation.add(x.getPlayer());
        }

        // Detect if the user is viewing their own team. If so, pass this information to jsp
        // so that it can display buttons that can take the user to the edit team page.
        User currentUser = authenticatedUserService.loadCurrentUser();
        Integer currentUserId = currentUser.getId();

        Integer currentUserTeam = fantasyTeamDAO.findByUserId(currentUserId).getId();

        if (Objects.equals(currentUserTeam, fantasyTeamId)) {
            Boolean canEdit = true;
            response.addObject("canEdit", canEdit);
        }

        response.addObject("teamInformationKey", teamInformation);
        response.addObject("playersKey", playerInformation);

        return response;
    }

    @GetMapping("/fantasy_team/create")
    public ModelAndView createFantasyTeam() {
        ModelAndView response = new ModelAndView();

        // check if user has a team already. if so, redirect them to view team page.
        // else, continue to create team page
        User currentUser = authenticatedUserService.loadCurrentUser();
        FantasyTeam fantasyTeam = fantasyTeamDAO.findByUserId(currentUser.getId());

        if (fantasyTeam == null) {
            response.setViewName("fantasy_team/create");
        } else {
            Integer teamId = fantasyTeam.getId();
            response.setViewName("redirect:/fantasy_team/view/" + teamId);
        }

        return response;
    }

    @PostMapping("/fantasy_team/createSubmit")
    public ModelAndView createFantasyTeamSubmit(@Valid CreateFantasyTeamFormBean form, BindingResult bindingResult, HttpSession session) {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            response.setViewName("fantasy_team/create");
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);
        } else {
            User currentUser = authenticatedUserService.loadCurrentUser();

            FantasyTeam fantasyTeam = new FantasyTeam();


            fantasyTeam.setTeamName(form.getTeamName());
            fantasyTeam.setUserId(currentUser.getId());

            fantasyTeamDAO.save(fantasyTeam);

            response.setViewName("redirect:/fantasy_team/view/" + fantasyTeam.getId());
        }
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


    // TODO - With what Eric said, I think a better implementation of this would be to have create simply be
    // TODO - to create a team id and name. Once you press the submit, the form bean goes through and you the team is created
    // TODO - then you are redirected to the edit screen. You can then add players. Things like budget are controlled
    // TODO - through the edit controller.
    // TODO - Or maybe, add budget as an attribute to the fantasy team. Also add a DAO method for "get total price of team"
    @GetMapping("/fantasy_team/edit/{fantasyTeamId}")
    public ModelAndView editFantasyTeam(@PathVariable Integer fantasyTeamId) {
        ModelAndView response = new ModelAndView();

        // check to see if this is current user's team. if not, redirect to home
        User currentUser = authenticatedUserService.loadCurrentUser();
        Integer currentUserTeam = fantasyTeamDAO.findByUserId(currentUser.getId()).getId();

        if (Objects.equals(currentUserTeam, fantasyTeamId)) {
            response.setViewName("fantasy_team/edit");

            // Load data of current user's team - roster, team name, remaining budget
            FantasyTeam currentFantasyTeam = fantasyTeamDAO.findById(fantasyTeamId);

            // Find current user's team's roster.
            List<FantasyPlayer> teamRoster = fantasyPlayerDAO.findPlayersByFantasyTeamId(fantasyTeamId);
            List<Player> playerInformation = new ArrayList<>();
            for (FantasyPlayer x : teamRoster) {
                playerInformation.add(x.getPlayer());
            }


            Integer budget = currentFantasyTeam.getBudget();
            String teamName = currentFantasyTeam.getTeamName();

            // Retrieve data for all real teams and their players.
            List<RealTeam> realTeams = realTeamDAO.findAllTeams();

            List<Player> players = new ArrayList<>();
            for (RealTeam rt : realTeams) {
                List<Player> roster = playerDAO.findPlayersByTeamActualId(rt.getId());
                players.addAll(roster);
            }

            response.addObject("teamName", teamName);
            response.addObject("currentTeamPlayersKey", playerInformation);
            response.addObject("budget", budget);
            response.addObject("realTeamsKey", realTeams);
            response.addObject("playersKey", players);
        } else {
            response.setViewName("redirect:/");
        }
        return response;

    }

    @PostMapping("/fantasy_team/addPlayer/{playerId}")
    public ModelAndView addPlayer( @PathVariable Integer playerId) {
        ModelAndView response = new ModelAndView();


        // check if user has 5 players already. if yes, redirect back to edit page; otherwise,
        // return some kind of error
        User currentUser = authenticatedUserService.loadCurrentUser();
        FantasyTeam currentTeam = fantasyTeamDAO.findByUserId(currentUser.getId());
        Player player = playerDAO.findById(playerId);
        // log.debug(String.valueOf(playerId));
        if ( fantasyTeamDAO.getTeamPlayerCount(currentTeam.getId()) < 5 ){

            // Check to see if this player is already in the team. Redirect back to the edit page (with
            // some sort of error message) if so. Otherwise, continue to add player to team.

            // Get roster of user's fantasy team
            // TODO - Surely, there must be a more efficient way to do this, right?
            // TODO - Isn't it  bad to have these super long chains of if/else statements?
            List<FantasyPlayer> currentTeamRoster = fantasyPlayerDAO.findPlayersByFantasyTeamId(currentTeam.getId());

            List<Player> players = new ArrayList<>();
            for (FantasyPlayer fp : currentTeamRoster) {
                players.add(fp.getPlayer());
            }

            if ( players.contains(player) ){
                log.debug("Error - player already in team");
                response.setViewName("redirect:/fantasy_team/edit/" + currentTeam.getId());
            } else {

                // Check to see if this player is within budget
                if ( player.getCost() > currentTeam.getBudget() ){
                    log.debug("Error - player too expensive");
                    response.setViewName("redirect:/fantasy_team/edit/" + currentTeam.getId());
                } else {
                    // Add player into database for this team.
                    FantasyPlayer fantasyPlayer = new FantasyPlayer();
                    fantasyPlayer.setPlayer(player);
                    fantasyPlayer.setFantasyTeam(currentTeam);
                    fantasyPlayerDAO.save(fantasyPlayer);

                    // Only 2 players in a fantasy team can be actual teammates in real life. If adding this player
                    // would make this team have 3 players that are real teammates, return to view screen and
                    // return error.
                    List<Integer> actualTeamCount = fantasyPlayerDAO.
                            getFantasyTeamActualTeamCountsByFantasyTeamId(currentTeam.getId());
                    if (actualTeamCount.contains(3)) {
                        // delete this record from the database
                        fantasyPlayerDAO.delete(fantasyPlayer);
                        log.debug("Error - too many players from the same real team");
                        response.setViewName("redirect:/fantasy_team/edit/" + currentTeam.getId());
                    } else {
                        // Finally, update team budget and redirect to view screen.
                        currentTeam.setBudget(currentTeam.getBudget() - player.getCost());
                        fantasyTeamDAO.save(currentTeam);
                        response.setViewName("redirect:/fantasy_team/view/" + currentTeam.getId());
                    }
                }
            }
        } else {
            // TODO - Need to add some sort of error, though... Maybe ask Eric about it tomorrow.
            response.setViewName("redirect:/fantasy_team/edit/" + currentTeam.getId());
            log.debug("Error here");
        }

        return response;
    }

    @PostMapping("/fantasy_team/dropPlayer/{playerId}")
    public ModelAndView dropPlayer(@PathVariable Integer playerId) {
        ModelAndView response = new ModelAndView();

        // For future reference
        // https://stackoverflow.com/questions/25821579/transactionrequiredexception-executing-an-update-delete-query
        // helped solve this. at least when deleting, @Transactional annotation needed in DAO

        // Get current user's team
        User currentUser = authenticatedUserService.loadCurrentUser();
        FantasyTeam currentTeam = fantasyTeamDAO.findByUserId(currentUser.getId());

        // Add requested player cost back into team budget
        Player player = playerDAO.findById(playerId);
        currentTeam.setBudget(currentTeam.getBudget() + player.getCost());

        // Drop requested player from team
        fantasyPlayerDAO.dropPlayer(currentTeam.getId(), playerId);

        response.setViewName("redirect:/fantasy_team/view/" + currentTeam.getId());

        return response;
    }


}
