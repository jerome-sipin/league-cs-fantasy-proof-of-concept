package com.fantasy.webapp.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePlayerFormBean {

    @NotEmpty
    private String playerName;

    @NotEmpty
    private Integer actualTeamId;

    @NotEmpty
    private String playerImageUrl;

}
