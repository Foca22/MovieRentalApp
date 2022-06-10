package com.application.movierentalapp.dto.exceptions;

public class PriceOutOfIntervalException extends Exception {

    public PriceOutOfIntervalException(String message) {
        super(message);
    }
}
