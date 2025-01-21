package com.fantasy.webapp.validation;

import com.fantasy.webapp.database.dao.RealTeamDAO;
import com.fantasy.webapp.database.entity.RealTeam;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class RealTeamNameUniqueImpl implements ConstraintValidator<RealTeamNameUnique, String> {

    public static final Logger LOG = LoggerFactory.getLogger(UsernameUniqueImpl.class);

    @Autowired
    private RealTeamDAO realTeamService;

    @Override
    public void initialize(RealTeamNameUnique constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){

        if (StringUtils.isEmpty(value)){
            return true;
        }

        RealTeam team = realTeamService.findByTeamNameExact(value);

        return (team == null);

    }

}
