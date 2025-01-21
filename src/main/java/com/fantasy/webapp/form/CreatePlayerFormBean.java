package com.fantasy.webapp.form;

import com.fantasy.webapp.validation.PlayerNameUnique;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePlayerFormBean {

    @PlayerNameUnique
    @NotEmpty
    private String playerName;

    @NotEmpty
    private Integer actualTeamId;

    @NotEmpty
    private String playerImageUrl;

}
