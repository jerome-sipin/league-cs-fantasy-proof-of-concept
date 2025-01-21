package com.fantasy.webapp.form;

import com.fantasy.webapp.database.entity.Player;
import com.fantasy.webapp.validation.TeamNameUnique;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateFantasyTeamFormBean {

    private Integer id;

    @NotEmpty
    @TeamNameUnique
    @Length(max = 50, message = "Team Name must be less than 50 characters")
    private String teamName;


}
