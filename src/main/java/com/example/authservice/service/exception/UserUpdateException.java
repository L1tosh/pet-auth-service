package com.example.authservice.service.exception;

public class UserUpdateException extends RuntimeException {

    private static final String USER_UPDATE_MESSAGE = "User with email %s exist";

    public UserUpdateException(String email) {
        super(USER_UPDATE_MESSAGE.formatted(email));
    }


}
