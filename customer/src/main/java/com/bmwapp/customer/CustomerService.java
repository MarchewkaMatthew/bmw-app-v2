package com.bmwapp.customer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer =
                Customer.builder().firstName(request.firstName()).lastName(request.lastName()).email(request.email())
                        .build();

        // TODO: check if email valid and not taken
        customerRepository.save(customer);
    }

    public Customer getCustomer(Integer id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Consumer with id %d not found", id)));
    }
}
