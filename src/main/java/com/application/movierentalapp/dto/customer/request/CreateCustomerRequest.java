package com.application.movierentalapp.dto.customer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {
    private String firstName;

    private String lastName;

    private String email;

    private Integer telephoneNumber;
}
