package com.example.authservice.service.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String USER_NOT_FOUND_ID_MESSAGE = "User with id %d not found";
    private static final String USER_NOT_FOUND_EMAIL_MESSAGE = "User with email %s not found";

    public UserNotFoundException(String email) {
        super(USER_NOT_FOUND_EMAIL_MESSAGE.formatted(email));
    }

    public UserNotFoundException(Long userId) {
        super(USER_NOT_FOUND_EMAIL_MESSAGE.formatted(userId));
    }
}
