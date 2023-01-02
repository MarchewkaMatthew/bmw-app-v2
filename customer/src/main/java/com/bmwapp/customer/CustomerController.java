package com.bmwapp.customer;

import com.bmwapp.customer.flat.FlatDto;
import com.bmwapp.customer.flat.GetFlatResponse;
import com.bmwapp.customer.model.Customer;
import com.bmwapp.customer.request.CustomerRegistrationRequest;
import com.bmwapp.customer.request.CustomerSetInvestmentRequest;
import com.bmwapp.customer.request.CustomerUpdateRequest;
import com.bmwapp.customer.response.GetCustomerResponse;
import com.bmwapp.customer.response.GetAllCustomersResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.security.RolesAllowed;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {


    @PostMapping
    public CustomerDto registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration {}", customerRegistrationRequest);
        Customer customer = customerService.registerCustomer(customerRegistrationRequest);
        return CustomerDto.FromDomain(customer); //dałem tak, bo przy rejestracji klienta nie pasuje tutaj GetCustomerResponse nie? a fajnie zwracać klienta (id) itp, którego dodaliśmy do bazy
    }

    @PutMapping
    public CustomerDto updateCustomer(@RequestBody CustomerUpdateRequest customerUpdateRequest) {
        log.info("update customer if exist {}", customerUpdateRequest);
        Customer updatedCustomer = customerService.updateCustomer(customerUpdateRequest);
        return CustomerDto.FromDomain(updatedCustomer);
    }

    @GetMapping(path = "{id}")
    public GetCustomerResponse getCustomer(@PathVariable("id") Integer id) {
        log.info("get customer with id: {}", id);

        Customer customer = customerService.getCustomer(id);
        return new GetCustomerResponse(CustomerDto.FromDomain(customer));
    };

    @GetMapping
    public GetAllCustomersResponse getCustomers(){
        log.info("get all customers");
        List<Customer> customersList = customerService.getAllCustomers();
        return new GetAllCustomersResponse(customersList.stream()
                .map(customer -> CustomerDto.FromDomain(customer))
                .collect(Collectors.toList()));
    }

//    @PostMapping("/setInvestment") //Nie wiem jak to można tutaj zrobić, żeby nie dzielić na inny microservice
//    public void setCustomerInvestment(@RequestBody CustomerSetInvestmentRequest customerSetInvestmentRequest){
//        log.info("Add investment to customer {}", customerSetInvestmentRequest);
//         customerService.addInvestmentToCustomer(customerSetInvestmentRequest);
//    }
}
