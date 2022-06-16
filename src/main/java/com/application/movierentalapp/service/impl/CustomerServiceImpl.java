package com.application.movierentalapp.service.impl;

import com.application.movierentalapp.dto.customer.request.CreateCustomerRequest;
import com.application.movierentalapp.dto.customer.request.UpdateCustomerRequest;
import com.application.movierentalapp.dto.customer.response.CustomerResponse;
import com.application.movierentalapp.dto.exceptions.customer.CustomerExceptionMessages;
import com.application.movierentalapp.dto.exceptions.customer.CustomerNotFoundException;
import com.application.movierentalapp.model.customer.Customer;
import com.application.movierentalapp.repository.CustomerRepo;
import com.application.movierentalapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer = new Customer(createCustomerRequest.getFirstName(), createCustomerRequest.getLastName(),
                createCustomerRequest.getEmail(), createCustomerRequest.getPhoneNumber());
        Customer savedCustomer = customerRepo.save(customer);
        CustomerResponse customerResponse = toCustomerResponse(savedCustomer);
        return customerResponse;
    }

    @Override
    public CustomerResponse getCustomer(Integer id) throws CustomerNotFoundException {
        Customer customer = findCustomerById(id);
        CustomerResponse customerResponse = toCustomerResponse(customer);
        return customerResponse;
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepo.findAll()
                .stream()
                .map(this::toCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse updateCustomer(UpdateCustomerRequest updateCustomerRequest) throws CustomerNotFoundException {
        Customer customer = findCustomerById(updateCustomerRequest.getId());
        customer.setEmail(updateCustomerRequest.getEmail());
        customer.setEmail(updateCustomerRequest.getEmail());

        Customer saveUpdatedCustomer = customerRepo.save(customer);
        CustomerResponse customerResponse = toCustomerResponse(saveUpdatedCustomer);
        return customerResponse;
    }

    @Override
    public void deleteCustomer(Integer id) throws CustomerNotFoundException {
        Customer customer = findCustomerById(id);
        customerRepo.delete(customer);
    }

    private CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(),
                customer.getPhoneNumber());
    }

    private Customer findCustomerById(Integer id) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if(customerOptional.isEmpty()){
            throw new CustomerNotFoundException(CustomerExceptionMessages.CUSTOMER_NOT_FOUND);
        }
        return customerOptional.get();
    }
}
