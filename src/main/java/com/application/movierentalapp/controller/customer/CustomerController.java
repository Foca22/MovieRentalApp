package com.application.movierentalapp.controller.customer;

import com.application.movierentalapp.dto.customer.request.CreateCustomerRequest;
import com.application.movierentalapp.dto.customer.request.UpdateCustomerRequest;
import com.application.movierentalapp.dto.customer.response.CustomerResponse;
import com.application.movierentalapp.dto.exceptions.customer.CustomerNotFoundException;
import com.application.movierentalapp.model.customer.Customer;
import com.application.movierentalapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //validari pt creare customer
    @PostMapping()
    ResponseEntity createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        CustomerResponse customerResponse = customerService.createCustomer(createCustomerRequest);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity getCustomer(@PathVariable Integer id) {
        try {
            CustomerResponse customerResponse = customerService.getCustomer(id);
            return ResponseEntity.ok(customerResponse);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    ResponseEntity getAllCustomers() {
        List<CustomerResponse> customerList = customerService.getAllCustomers();
        return ResponseEntity.ok(customerList);
    }

    @PutMapping
    ResponseEntity updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
        try {
            CustomerResponse customerResponse = customerService.updateCustomer(updateCustomerRequest);
            return ResponseEntity.ok(customerResponse);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteCustomer(@PathVariable Integer id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
