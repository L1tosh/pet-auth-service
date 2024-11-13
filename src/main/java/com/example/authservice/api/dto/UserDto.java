package com.example.authservice.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    @NotNull(message = "Id cannot ne null")
    Long id;

    @NotBlank(message = "Name cannot be blank")
    String name;

    @NotBlank(message = "Surname cannot be blank")
    String surname;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    String email;
}
