package com.example.authservice.service;

import com.example.authservice.domain.Token;
import com.example.authservice.domain.User;

public interface AuthService {
    Token attemptLogin(String email, String password);
    User register(User user);
    Token refreshToken(String refreshToken);
}
