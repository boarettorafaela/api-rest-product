package com.project.pagbank.handler;

import com.project.pagbank.model.ExceptionsResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionsHandler {
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionsResponse NotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return new ExceptionsResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method not allowed");
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ExceptionsResponse NotSupportedException(EntityNotFoundException exception) {
        return new ExceptionsResponse(HttpStatus.NOT_FOUND.value(), "Not Found");
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ExceptionsResponse NotSupportedException(EmptyResultDataAccessException exception) {
        return new ExceptionsResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionsResponse NotSupportedException(MethodArgumentNotValidException exception) {
        return new ExceptionsResponse(HttpStatus.BAD_REQUEST.value(), "Bad request");
    }
}