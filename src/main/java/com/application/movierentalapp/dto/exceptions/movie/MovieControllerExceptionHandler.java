package com.application.movierentalapp.dto.exceptions.movie;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MovieControllerExceptionHandler {

//    @ExceptionHandler(value = DataIntegrityViolationException.class)
//    public ResponseEntity handleMovieLengthTooBigException(DataIntegrityViolationException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MovieExceptionMessages.MOVIE_NAME_TOO_BIG);
//    }

//    @ExceptionHandler({MovieNameTooBigException.class})
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public MovieErrorDto handleMovieLengthTooBigException (HttpServletRequest request, Exception exception) {
//        return handleBaseException(exception, HttpStatus.BAD_REQUEST);
//    }
//
//    private MovieErrorDto handleBaseException(Exception exception, HttpStatus status) {
//        Exception exception1 = new Exception();
//
//        return new MovieErrorDto(exception1.getLocalizedMessage(),
//                exception1.getMessage(), status.value());
//    }
}
