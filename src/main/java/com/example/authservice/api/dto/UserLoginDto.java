package com.example.authservice.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserLoginDto {
    @Email
    @NotBlank(message = "email can't be empty")
    String username;

    @NotBlank(message = "password can't be empty")
    String password;
}
