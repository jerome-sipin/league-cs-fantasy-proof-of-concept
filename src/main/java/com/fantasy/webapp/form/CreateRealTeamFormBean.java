package com.fantasy.webapp.form;

import com.fantasy.webapp.validation.RealTeamNameUnique;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRealTeamFormBean {

    @RealTeamNameUnique
    @NotEmpty
    private String teamName;

}
