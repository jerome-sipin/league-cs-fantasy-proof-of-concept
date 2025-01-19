package com.fantasy.webapp.validation;

import com.fantasy.webapp.database.dao.UserDAO;
import com.fantasy.webapp.database.entity.User;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UsernameUniqueImpl implements ConstraintValidator<UsernameUnique, String> {

    public static final Logger LOG = LoggerFactory.getLogger(UsernameUniqueImpl.class);

    @Autowired
    private UserDAO userService;

    @Override
    public void initialize(UsernameUnique constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){

        if (StringUtils.isEmpty(value)){
            return true;
        }

        User user = userService.findByUsernameExact(value);

        return (user == null);

    }

}
