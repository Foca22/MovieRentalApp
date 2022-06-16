package com.application.movierentalapp.dto.customer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}
