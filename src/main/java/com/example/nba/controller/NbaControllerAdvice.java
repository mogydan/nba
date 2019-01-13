package com.example.nba.controller;

import com.example.nba.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class NbaControllerAdvice {

    private static final String INTERNAL_SERVER_ERROR_DEFAULT_MESSAGE = "An internal error occurred. Please contact your system administrator.";

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public String notFoundException(ResourceNotFoundException exception) {
        log.warn(exception.getMessage(), exception);
        return exception.getMessage();
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String defaultException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return INTERNAL_SERVER_ERROR_DEFAULT_MESSAGE;
    }
}
