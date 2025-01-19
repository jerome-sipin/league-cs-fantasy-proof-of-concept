package com.fantasy.webapp.validation;

import com.fantasy.webapp.database.dao.FantasyTeamDAO;
import com.fantasy.webapp.database.entity.FantasyTeam;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TeamNameUniqueImpl implements ConstraintValidator<TeamNameUnique, String> {

    public static final Logger LOG = LoggerFactory.getLogger(TeamNameUniqueImpl.class);

    @Autowired
    private FantasyTeamDAO fantasyTeamService;

    @Override
    public void initialize(TeamNameUnique constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        FantasyTeam team = fantasyTeamService.findByTeamName(value);

        return (team == null);
    }

}
