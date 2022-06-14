package com.application.movierentalapp.dto.exceptions.movie;

public class MovieNotFoundException extends Exception {

    public MovieNotFoundException(String message) {
        super(message);
    }
}
