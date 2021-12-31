package com.cloud_course.demo.service.validator.impl;

import com.cloud_course.demo.controller.advice.exceptions.BusinessValidationException;
import com.cloud_course.demo.model.response.UpdateUserRequest;
import com.cloud_course.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlreadyExistingUser {

    private final UserRepository userRepository;

    public void validate(UpdateUserRequest updateUserRequest) {
        if (userRepository.findByEmail(updateUserRequest.getEmail()).isPresent()) {
            log.error("Email address {} already in user", updateUserRequest.getEmail());
            throw new BusinessValidationException("Email address already in use",
                    updateUserRequest.getEmail());
        }
    }
}
