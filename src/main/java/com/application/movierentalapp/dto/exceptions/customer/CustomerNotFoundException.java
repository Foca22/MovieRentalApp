package com.application.movierentalapp.dto.exceptions.customer;

public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(String message){
        super(message);
    }
}
