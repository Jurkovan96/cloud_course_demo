package com.cloud_course.demo.controller.advice.exceptions;

import lombok.Getter;

@Getter
public class BusinessValidationException extends RuntimeException {

    private final Object[] arguments;

    public BusinessValidationException(String message, Object... arguments) {
        super(message);
        this.arguments = arguments;
    }
}
