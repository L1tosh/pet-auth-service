package com.example.authservice.api.dto;

import com.example.authservice.api.dto.validation.ValidTokenFormat;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TokenDto {
    @NotBlank
    @ValidTokenFormat
    String token;

    @JsonCreator
    public TokenDto(@JsonProperty("token") String token) {
        this.token = token;
    }
}
