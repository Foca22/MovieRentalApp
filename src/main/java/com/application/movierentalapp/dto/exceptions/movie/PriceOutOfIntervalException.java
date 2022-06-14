package com.application.movierentalapp.dto.exceptions.movie;

public class PriceOutOfIntervalException extends Exception {

    public PriceOutOfIntervalException(String message) {
        super(message);
    }
}
