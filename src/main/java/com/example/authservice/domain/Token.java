package com.example.authservice.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Token {
    String token;
}
