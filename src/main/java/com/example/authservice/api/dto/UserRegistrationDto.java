package com.example.authservice.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserRegistrationDto {
    @NotBlank(message = "Name must not be blank.")
    @Size(min = 3, message = "Name must be at least 3 characters long.")
    @Size(max = 48, message = "Name must not exceed 48 characters.")
    String name;

    @NotBlank(message = "Surname must not be blank.")
    @Size(min = 3, message = "Surname must be at least 3 characters long.")
    @Size(max = 48, message = "Surname must not exceed 48 characters.")
    String surname;

    @NotBlank(message = "Password must not be blank.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    @Size(max = 48, message = "Password must not exceed 48 characters.")
    String password;

    @Email(message = "Email must be a valid email address.")
    @Size(max = 64, message = "Email must not exceed 64 characters.")
    String email;
}
