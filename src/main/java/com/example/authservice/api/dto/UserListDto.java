package com.example.authservice.api.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UserListDto {
    List<UserDto> userDtos;
}
