package com.fantasy.webapp.form;

import com.fantasy.webapp.database.entity.Player;
import com.fantasy.webapp.validation.TeamNameUnique;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class CreateFantasyTeamFormBean {

    // TODO - ????
    // How would this work???
    // Probably needs to be appended to FantasyPlayers table. Put each player id that has been selected into a
    // list, then for loop that and append it to the database. Refer to in-class example for appending.

    /// don't even need a form bean for this

    private Integer id;

     @NotEmpty
     @TeamNameUnique
     @Length(max = 50, message = "Team Name must be less than 50 characters")
    private String teamName;


}
