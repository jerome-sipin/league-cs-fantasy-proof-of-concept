package com.fantasy.webapp.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrentPasswordValidImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentPasswordValid {

    String message() default "{Invalid password!}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}