package com.fantasy.webapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TeamNameUniqueImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TeamNameUnique {

    String message() default "{Team name must be unique!}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
