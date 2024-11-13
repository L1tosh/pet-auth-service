package com.example.authservice.api.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TokenFormatValidator.class)
public @interface ValidTokenFormat {
    String message() default "Invalid token format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
