package com.cloud_course.demo.service;

import com.cloud_course.demo.controller.advice.exceptions.ResourceNotFoundException;
import com.cloud_course.demo.entity.User;
import com.cloud_course.demo.model.CreateUserRequest;
import com.cloud_course.demo.model.response.UpdateUserRequest;
import com.cloud_course.demo.repository.UserRepository;
import com.cloud_course.demo.service.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public User findUser(Long userId) {
        log.info("Retrieving user with id {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", userId));
    }

    public Collection<User> findAllUsers() {
        log.info("Retrieving all users");
        return userRepository.findAll();
    }

    public User createNewUser(CreateUserRequest createUserRequest) {
        userValidator.validateUserRegistration(createUserRequest);
        log.info("Creating new user with {}", createUserRequest.getName());
        return userRepository.save(User.builder()
                .email(createUserRequest.getEmail())
                .name(createUserRequest.getName())
                .build());
    }

    public User updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        log.info("Update existing user with {}", updateUserRequest.getName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", userId));
        userValidator.validateUserUpdate(updateUserRequest);
        user.setEmail(updateUserRequest.getEmail());
        user.setName(updateUserRequest.getName());
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found", userId));
        log.info("Deleting existing user {}", user.getName());
        userRepository.delete(user);
    }
}
