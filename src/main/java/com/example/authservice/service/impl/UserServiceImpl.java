package com.example.authservice.service.impl;

import com.example.authservice.data.UserRepository;
import com.example.authservice.domain.User;
import com.example.authservice.service.UserService;
import com.example.authservice.service.exception.UserCreateException;
import com.example.authservice.service.exception.UserNotFoundException;
import com.example.authservice.service.exception.UserUpdateException;
import com.example.authservice.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            log.info("User with id {} not found", userId);
            return new UserNotFoundException(userId);
        });
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            log.info("User with email {} not found", email);
            return new UserNotFoundException(email);
        });
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.info("User with email {} exist", user.getEmail());
            throw new UserCreateException(user.getEmail());
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(Long userId, User updatedUser) {
        var userToUpdate = getUserById(userId);

        if (!updatedUser.getEmail().equals(userToUpdate.getEmail()) && userRepository.findByEmail(updatedUser.getEmail()).isPresent())
                throw new UserUpdateException(updatedUser.getEmail());

        userMapper.updateUser(updatedUser, userToUpdate);

        return userRepository.save(userToUpdate);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
        } else {
            log.warn("Attempt to delete user with id {}", userId);
        }
    }
}
