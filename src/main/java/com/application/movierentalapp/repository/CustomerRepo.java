package com.application.movierentalapp.repository;

import com.application.movierentalapp.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
