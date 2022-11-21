package com.bmwapp.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer =
                Customer.builder().firstName(request.firstName()).lastName(request.lastName()).email(request.email())
                        .build();

        // TODO: check if email valid and not taken
        customerRepository.save(customer);
    }
}
