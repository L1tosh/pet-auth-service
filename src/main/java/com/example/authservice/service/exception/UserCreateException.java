package com.example.authservice.service.exception;

public class UserCreateException extends RuntimeException {
    private static final String USER_CREATE_MESSAGE = "User with email %s exist";

    public UserCreateException(String email) {
        super(USER_CREATE_MESSAGE.formatted(email));
    }
}
