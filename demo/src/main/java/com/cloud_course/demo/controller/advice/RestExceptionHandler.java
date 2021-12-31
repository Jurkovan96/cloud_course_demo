package com.cloud_course.demo.controller.advice;

import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.cloud_course.demo.controller.advice.exceptions.BusinessValidationException;
import com.cloud_course.demo.controller.advice.exceptions.ResourceNotFoundException;
import com.cloud_course.demo.model.response.Error;
import com.cloud_course.demo.model.response.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final HttpHeaders HEADERS = new HttpHeaders();

    static {
        HEADERS.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<Error> errorList = ex.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> Error.builder()
                        .message(objectError.getDefaultMessage())
                        .errorCode(400)
                        .build()).toList();
        log.error("Data not valid and contains {} error(s)", errorList.size());
        return new ResponseEntity<>(ErrorDetails.builder()
                .localDateTime(LocalDateTime.now())
                .errors(errorList)
                .build(), HEADERS, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .errors(Collections
                        .singletonList(Error.builder()
                                .message(exception.getMessage())
                                .errorCode(404)
                                .build()))
                .localDateTime(LocalDateTime.now())
                .build(), HEADERS, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<ErrorDetails> handleBusinessValidationException(BusinessValidationException exception,
                                                                          WebRequest webRequest) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .errors(Collections
                        .singletonList(Error.builder()
                                .message(exception.getMessage())
                                .errorCode(400)
                                .build()))
                .localDateTime(LocalDateTime.now())
                .build(), HEADERS, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AmazonRekognitionException.class)
    public ResponseEntity<ErrorDetails> handleAmazonRekognitionException(AmazonRekognitionException exception,
                                                                         WebRequest webRequest) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .errors(Collections
                        .singletonList(Error.builder()
                                .message(exception.getMessage())
                                .errorCode(404)
                                .build()))
                .localDateTime(LocalDateTime.now())
                .build(), HEADERS, HttpStatus.NOT_FOUND);
    }
}