package com.example.authservice.api.dto.validation;

import com.auth0.jwt.JWT;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Instant;

public class TokenFormatValidator implements ConstraintValidator<ValidTokenFormat, String> {
    @Override
    public boolean isValid(String token, ConstraintValidatorContext constraintValidatorContext) {
        if (token == null || !token.matches("^[A-Za-z0-9-_.]+$")) {
            return false;
        }

        try {
            var decodedJWT = JWT.decode(token);

            String userId = decodedJWT.getSubject();
            String email = decodedJWT.getClaim("e").asString();

            if (userId == null || email == null || email.isBlank()) {
                return false;
            }

            return !decodedJWT.getExpiresAt().toInstant().isBefore(Instant.now());

        } catch (Exception e) {
            return false;
        }
    }
}
