package com.example.authservice.api;

import com.example.authservice.api.dto.UserDto;
import com.example.authservice.api.dto.UserListDto;
import com.example.authservice.service.UserService;
import com.example.authservice.service.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.toUserDto(userService.getUserById(id)));
    }

    @GetMapping
    public ResponseEntity<UserListDto> getAllUsers() {
        return ResponseEntity.ok(userMapper.toUserListDto(userService.getAllUsers()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto updatedUser) {
        var savedUser = userService.updateUser(id, userMapper.toUser(updatedUser));

        return ResponseEntity.ok(userMapper.toUserDto(savedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
