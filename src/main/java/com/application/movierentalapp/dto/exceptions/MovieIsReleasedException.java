package com.application.movierentalapp.dto.exceptions;

public class MovieIsReleasedException extends Exception {
    public MovieIsReleasedException(String message) {
        super(message);
    }
}
