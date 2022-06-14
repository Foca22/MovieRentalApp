package com.application.movierentalapp.service;

import com.application.movierentalapp.dto.customer.request.CreateCustomerRequest;
import com.application.movierentalapp.dto.customer.request.UpdateCustomerRequest;
import com.application.movierentalapp.dto.customer.response.CustomerResponse;
import com.application.movierentalapp.dto.exceptions.customer.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);

    CustomerResponse getCustomer(Integer id) throws CustomerNotFoundException;

    List<CustomerResponse> getAllCustomers();

    CustomerResponse updateCustomer(UpdateCustomerRequest updateCustomerRequest) throws CustomerNotFoundException;

    void deleteCustomer(Integer id) throws CustomerNotFoundException;

}
