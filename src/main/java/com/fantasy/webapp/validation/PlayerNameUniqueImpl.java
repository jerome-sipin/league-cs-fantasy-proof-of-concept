package com.fantasy.webapp.validation;

import com.fantasy.webapp.database.dao.PlayerDAO;
import com.fantasy.webapp.database.dao.UserDAO;
import com.fantasy.webapp.database.entity.Player;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerNameUniqueImpl implements ConstraintValidator<PlayerNameUnique, String> {

    public static final Logger LOG = LoggerFactory.getLogger(UsernameUniqueImpl.class);

    @Autowired
    private PlayerDAO playerService;

    @Override
    public void initialize(PlayerNameUnique constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if (StringUtils.isEmpty(value)){
            return true;
        }

        Player player = playerService.findPlayerByNameExact(value);

        return (player == null);
    }

}
