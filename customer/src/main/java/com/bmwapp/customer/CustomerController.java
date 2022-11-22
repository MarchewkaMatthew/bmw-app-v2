package com.bmwapp.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }

    @GetMapping(path = "{id}")
    public GetCustomerResponse getCustomer(@PathVariable("id") Integer id) {
        log.info("get customer with id: {}", id);

        Customer customer = customerService.getCustomer(id);
        return new GetCustomerResponse(CustomerDto.FromDomain(customer));
    };
}
