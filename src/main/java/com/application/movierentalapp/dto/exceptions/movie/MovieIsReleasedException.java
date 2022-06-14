package com.application.movierentalapp.dto.exceptions.movie;

public class MovieIsReleasedException extends Exception {
    public MovieIsReleasedException(String message) {
        super(message);
    }
}
