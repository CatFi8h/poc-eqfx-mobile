package com.poc.equifax.demo.web;

import com.poc.equifax.demo.web.exception.DataSourceNotFoundException;
import com.poc.equifax.demo.web.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataSourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataSourceNotFoundException(DataSourceNotFoundException ex) {
        return handleError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleDataSourceNotFoundException(MethodArgumentTypeMismatchException ex) {
        return handleError(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> handleError(String message, HttpStatus badRequest) {
        log.warn("Handle exception: {}", message);
        ErrorResponse errorResponse = ErrorResponse.of(Instant.now(), message);
        return new ResponseEntity<>(errorResponse, badRequest);
    }
}
