package com.cloud_course.demo.controller;

import com.cloud_course.demo.entity.User;
import com.cloud_course.demo.model.CreateUserRequest;
import com.cloud_course.demo.model.response.UpdateUserRequest;
import com.cloud_course.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        log.info("Get user request");
        return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<Collection<User>> geAlltUser() {
        log.info("Get all users request");
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        log.info("Create user request");
        return new ResponseEntity<>(userService.createNewUser(createUserRequest), HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest,
                                           @PathVariable Long id) {
        log.info("Update user request");
        return new ResponseEntity<>(userService.updateUser(id, updateUserRequest), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        log.info("Delete user request");
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
