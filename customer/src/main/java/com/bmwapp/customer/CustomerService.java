package com.bmwapp.customer;

import com.bmwapp.customer.flat.GetFlatResponse;
import com.bmwapp.customer.model.Customer;
import com.bmwapp.customer.model.Investment;
import com.bmwapp.customer.repository.CustomerRepository;
import com.bmwapp.customer.request.CustomerRegistrationRequest;
import com.bmwapp.customer.request.CustomerSetInvestmentRequest;
import com.bmwapp.customer.request.CustomerUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public Customer registerCustomer(CustomerRegistrationRequest request) {
        Customer customer =
                Customer.builder().firstName(request.firstName()).lastName(request.lastName()).email(request.email())
                        .build();

        // TODO: check if email valid and not taken
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(CustomerUpdateRequest request){
        Customer customer = customerRepository
                .findById(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Consumer with id %d not found", request.id())));

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Integer id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Customer with id %d not found", id)));
<<<<<<< Updated upstream
    }

    public void addInvestmentToCustomer(CustomerSetInvestmentRequest request) {
            GetFlatResponse
                    response = restTemplate
                    .getForObject("http://FLAT/api/v1/flats/{id}", GetFlatResponse.class,
                            request.flatId());

            log.info("Flat test tutaj... {}", response);


            Customer customer = customerRepository.findById(request.customerId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Customer with id %d not found", request.customerId())));

            Investment investment = Investment.builder()
                    .status("Nowa inwestycja")
                    .flatDto(response.flatDto())
                    .build();

            customer.addInvestment(investment);
            customerRepository.save(customer);
    }

=======
    }

//    public void addInvestmentToCustomer(CustomerSetInvestmentRequest request) {
//            GetFlatResponse
//                    response = restTemplate
//                    .getForObject("http://FLAT/api/v1/flats/{id}", GetFlatResponse.class,
//                            request.flatId());
//
//            log.info("Flat test tutaj... {}", response);
//
//
//            Customer customer = customerRepository.findById(request.customerId())
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    String.format("Customer with id %d not found", request.customerId())));
//
//            Investment investment = Investment.builder()
//                    .status("Nowa inwestycja")
//                    .flatDto(response.flatDto())
//                    .build();
//
//            customer.addInvestment(investment);
//            customerRepository.save(customer);
//    }

>>>>>>> Stashed changes
}
