package com.example.authservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must not be blank.")
    @Size(min = 3, message = "Name must be at least 3 characters long.")
    @Size(max = 48, message = "Name must not exceed 48 characters.")
    @Column(name = "name", unique = true)
    private String name;

    @NotBlank(message = "Surname must not be blank.")
    @Size(min = 3, message = "Surname must be at least 3 characters long.")
    @Size(max = 48, message = "Surname must not exceed 48 characters.")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Password must not be blank.")
    @Column(name = "password", nullable = false)
    private String password;

    @Email(message = "Email must be a valid email address.")
    @Size(max = 64, message = "Email must not exceed 64 characters.")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
