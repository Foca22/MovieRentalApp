package com.application.movierentalapp.dto.customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {

    @NotEmpty(message = "first.name.cannot.be.empty")
    private String firstName;

    @NotNull(message = "last.name.cannot.be.empty")
    private String lastName;

    @NotNull(message = "email.cannot.be.empty")
    private String email;

    @NotNull(message = "telephone.number.cannot.be.empty")
    private String phoneNumber;
}
