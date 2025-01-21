package com.fantasy.webapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RealTeamNameUniqueImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RealTeamNameUnique {

    // TODO - Isn't this what shows when there is an error? Edit this later.
    String message() default "Team Name must be unique!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
