package com.fantasy.webapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameUniqueImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameUnique {

    String message() default "{UsernameUnique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
