package com.cloud_course.demo.service.validator;

import com.cloud_course.demo.model.CreateUserRequest;
import com.cloud_course.demo.model.response.UpdateUserRequest;
import com.cloud_course.demo.service.validator.impl.AlreadyExistingUser;
import com.cloud_course.demo.service.validator.impl.UserAlreadyCreated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidator {

    private final UserAlreadyCreated createUserValidators;
    private final AlreadyExistingUser updateUsers;

    public void validateUserRegistration(CreateUserRequest createUserRequest) {
        log.info("Validating request for user {}", createUserRequest.getName());
        createUserValidators.validate(createUserRequest);
    }

    public void validateUserUpdate(UpdateUserRequest updateUserRequest) {
        log.info("Validating update request for user {}", updateUserRequest.getName());
        updateUsers.validate(updateUserRequest);
    }
}
