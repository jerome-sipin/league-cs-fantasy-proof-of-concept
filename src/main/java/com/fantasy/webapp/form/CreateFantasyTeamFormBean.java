package com.fantasy.webapp.form;

import com.fantasy.webapp.database.entity.Player;

public class CreateFantasyTeamFormBean {

    // TODO - ????
    // How would this work???
    // Probably needs to be appended to FantasyPlayers table. Put each player id that has been selected into a
    // list, then for loop that and append it to the database. Refer to in-class example for appending.

    /// don't even need a form bean for this

    private Integer id;

    // @NotEmpty
    // @Unique
    // @Length(max = 50, message = "Team Name must be less than 50 characters")
    private String teamName;

    // TODO - Do form beans have to match the constructors? Can't I just have something like this?
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;

}
