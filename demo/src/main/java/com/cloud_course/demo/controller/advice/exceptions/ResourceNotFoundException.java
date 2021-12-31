package com.cloud_course.demo.controller.advice.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final Object[] arguments;

    public ResourceNotFoundException(String message, Object... arguments) {
        super(message);
        this.arguments = arguments;
    }
}
