package com.example.authservice.api.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RefreshTokenDto {
    String token;
    String refreshToken;
}
