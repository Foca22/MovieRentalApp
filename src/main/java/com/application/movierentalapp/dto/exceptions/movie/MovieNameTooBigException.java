package com.application.movierentalapp.dto.exceptions.movie;

public class MovieNameTooBigException extends Exception{
    public MovieNameTooBigException(String message) {
        super(message);
    }
}
