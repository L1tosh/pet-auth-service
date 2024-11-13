package com.example.authservice.api;

import com.example.authservice.api.dto.*;
import com.example.authservice.domain.Token;
import com.example.authservice.service.AuthService;
import com.example.authservice.service.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody @Valid UserLoginDto request) {
        log.info("Attempt to get token by user with username {}", request.getUsername());
        return ResponseEntity.ok(authService.attemptLogin(request.getUsername(), request.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserRegistrationDto registrationDto) {
        var savedUser = authService.register(userMapper.toUser(registrationDto));

        log.info("User register with email {}", registrationDto.getEmail());
        return ResponseEntity
                .created(URI.create("/api/v1/users/%d".formatted(savedUser.getId())))
                .body(userMapper.toUserDto(savedUser));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenDto> refreshToken(@RequestBody @Valid TokenDto tokenDto) {
        var refreshToken = authService.refreshToken(tokenDto.getToken());

        return ResponseEntity.ok(RefreshTokenDto.builder()
                .token(tokenDto.getToken())
                .refreshToken(refreshToken.getToken()).build());
    }

}
