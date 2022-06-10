package com.application.movierentalapp.dto.exceptions;

public class MovieNameTooBigException extends Exception{
    public MovieNameTooBigException(String message) {
        super(message);
    }
}
