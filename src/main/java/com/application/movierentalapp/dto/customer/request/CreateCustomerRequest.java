package com.application.movierentalapp.dto.customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {

    @NotEmpty(message = "first.name.cannot.be.empty")
    private String firstName;

    @NotEmpty(message = "last.name.cannot.be.empty")
    private String lastName;

    @Email
    @NotEmpty(message = "email.cannot.be.empty")
    private String email;

    @NotEmpty(message = "phone.number.cannot.be.empty")
    private String phoneNumber;
}
