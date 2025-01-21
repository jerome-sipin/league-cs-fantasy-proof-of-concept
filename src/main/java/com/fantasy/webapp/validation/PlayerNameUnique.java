package com.fantasy.webapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PlayerNameUniqueImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PlayerNameUnique {

    String message() default "{PlayerNameUnique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
