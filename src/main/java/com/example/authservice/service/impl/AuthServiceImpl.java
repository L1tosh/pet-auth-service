package com.example.authservice.service.impl;


import com.example.authservice.auth.UserPrincipal;
import com.example.authservice.auth.exception.TokenValidationException;
import com.example.authservice.auth.jwt.JwtDecoder;
import com.example.authservice.auth.jwt.JwtIssuer;
import com.example.authservice.domain.Token;
import com.example.authservice.domain.User;
import com.example.authservice.service.AuthService;
import com.example.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtDecoder jwtDecoder;
    private final JwtIssuer jwtIssuer;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @Override
    public Token attemptLogin(String email, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principal = (UserPrincipal) authentication.getPrincipal();

        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);

        return Token.builder()
                .token(token)
                .build();
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
    }

    @Override
    public Token refreshToken(String refreshToken) {
        var decodedJWT = jwtDecoder.decode(refreshToken);

        if (decodedJWT.getExpiresAt().toInstant().isBefore(Instant.now())) {
            throw new TokenValidationException("Refresh token has expired");
        }

        String userId = decodedJWT.getSubject();
        var user = userService.getUserById(Long.parseLong(userId));

        var token = jwtIssuer.issue(user.getId(), user.getEmail(), Collections.emptyList());

        return Token.builder()
                .token(token)
                .build();
    }

}
