package com.fantasy.webapp.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRealTeamFormBean {

    // @TeamNameUnique
    @NotEmpty
    private String teamName;

}
