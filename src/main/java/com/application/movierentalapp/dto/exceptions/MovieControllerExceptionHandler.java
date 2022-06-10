package com.application.movierentalapp.dto.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieControllerExceptionHandler {

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity handleMovieLengthTooBigException(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionMessages.MOVIE_NAME_TOO_BIG);
    }
}
