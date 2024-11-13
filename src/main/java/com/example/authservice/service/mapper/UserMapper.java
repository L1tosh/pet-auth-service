package com.example.authservice.service.mapper;

import com.example.authservice.api.dto.UserDto;
import com.example.authservice.api.dto.UserListDto;
import com.example.authservice.api.dto.UserRegistrationDto;
import com.example.authservice.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "email", target = "email")
    User toUser(UserDto userDto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "email", target = "email")
    @Named("toUserDto")
    UserDto toUserDto(User user);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User toUser(UserRegistrationDto userRegistrationDto);

    List<User> toUser(List<UserDto> userDtoList);
    List<UserDto> toUserDto(List<User> userList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateUser(User updatedUser, @MappingTarget User userToUpdate);

    @Named("toUserListDto")
    default UserListDto toUserListDto(List<User> userList) {
        if (userList == null) return null;

        return UserListDto.builder().userDtos(
                userList.stream()
                        .map(this::toUserDto)
                        .toList()
        ).build();
    }

    @Named("toUserList")
    default List<User> toUserList(UserListDto userListDto) {
        if (userListDto == null || userListDto.getUserDtos() == null) return Collections.emptyList();

        return userListDto.getUserDtos().stream()
                .map(this::toUser)
                .toList();
    }
}
