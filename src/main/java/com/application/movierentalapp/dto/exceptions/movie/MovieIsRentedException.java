package com.application.movierentalapp.dto.exceptions.movie;

public class MovieIsRentedException extends Exception {
    public MovieIsRentedException(String message) {
        super(message);
    }
}
