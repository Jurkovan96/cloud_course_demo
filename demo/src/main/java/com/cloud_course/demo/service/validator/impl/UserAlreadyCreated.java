package com.cloud_course.demo.service.validator.impl;


import com.cloud_course.demo.controller.advice.exceptions.BusinessValidationException;
import com.cloud_course.demo.model.CreateUserRequest;
import com.cloud_course.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserAlreadyCreated {

    private final UserRepository userRepository;

    public void validate(CreateUserRequest createUserRequest) {
        if (userRepository.findByEmail(createUserRequest.getEmail()).isPresent()) {
            log.error("An user with the email address {} already exists", createUserRequest.getEmail());
            throw new BusinessValidationException("User with email already exists!",
                    createUserRequest.getEmail());
        }
    }
}
