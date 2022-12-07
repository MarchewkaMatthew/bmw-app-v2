package com.bmwapp.customer;

import com.microsoft.applicationinsights.TelemetryClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    TelemetryClient telemetryClient;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration {}", customerRegistrationRequest);
        telemetryClient.trackEvent("New customer registration");
        customerService.registerCustomer(customerRegistrationRequest);
    }

    @GetMapping(path = "{id}")
    public GetCustomerResponse getCustomer(@PathVariable("id") Integer id) {
        log.info("get customer with id: {}", id);

        Customer customer = customerService.getCustomer(id);
        return new GetCustomerResponse(CustomerDto.FromDomain(customer));
    };
}
