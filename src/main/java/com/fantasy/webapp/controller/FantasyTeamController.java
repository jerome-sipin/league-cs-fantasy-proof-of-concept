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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        log.debug("Current user: {}", currentUser);
        if (currentUser != null) {
            Integer currentUserId = currentUser.getId();

            Integer currentUserTeam = fantasyTeamDAO.findByUserId(currentUserId).getId();

            if (Objects.equals(currentUserTeam, fantasyTeamId)) {
                Boolean canEdit = true;
                response.addObject("canEdit", canEdit);
            }
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
    public ModelAndView createFantasyTeamSubmit(@Valid CreateFantasyTeamFormBean form, BindingResult bindingResult) {
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
    public ModelAndView addPlayer( @PathVariable Integer playerId, RedirectAttributes attributes) {
        ModelAndView response = new ModelAndView();

        // TODO - Rewrite this function if needed. It should be fine for HLTV-style logic in team drafting,
        //  but what if we ever want to do like, irl football style drafting. That would require a lot more
        //  logic that would make these nested if else statements hard to follow.
        //  Do return response in the same "cell" as the set view.

        // todo - Okay might have to ask Eric about this. The solution here is clearly to use RedirectAttributes,
        //  yet I can't get it to appear in the edit jsp.
        // https://stackoverflow.com/questions/12633916/spring-how-to-pass-a-message-between-views
        // https://www.baeldung.com/spring-web-flash-attributes
        // pass objects to fantasy_team/edit from this view

        Boolean playerExists = false;
        Boolean tooExpensive = false;
        Boolean tooMany = false;
        Boolean fullRoster = false;

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
                playerExists = true;
                response.setViewName("fantasy_team/edit");
                response.setViewName("redirect:/fantasy_team/edit/" + currentTeam.getId());
                attributes.addFlashAttribute("playerExists", playerExists);
                // TODO
                // return response;
                // can remove else now
                // to display error - either look up bootstrap alert box or add object to the jsp
                // that will then display some html telling the user the error
            } else {

                // Check to see if this player is within budget
                if ( player.getCost() > currentTeam.getBudget() ){
                    log.debug("Error - player too expensive");
                    tooExpensive = true;
                    response.setViewName("fantasy_team/edit");
                    response.setViewName("redirect:/fantasy_team/edit/" + currentTeam.getId());
                    attributes.addFlashAttribute("tooExpensive", tooExpensive);
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
                        tooMany = true;
                        response.setViewName("fantasy_team/edit");
                        response.setViewName("redirect:/fantasy_team/edit/" + currentTeam.getId());
                        attributes.addFlashAttribute("tooMany", tooMany);
                        
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
            fullRoster = true;
            response.setViewName("fantasy_team/edit");
            response.setViewName("redirect:/fantasy_team/edit/" + currentTeam.getId());
            attributes.addFlashAttribute("fullRoster", fullRoster);
            log.debug("Error - you already have a full roster");
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
