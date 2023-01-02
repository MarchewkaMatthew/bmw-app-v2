package com.bmwapp.customer.response;

import com.bmwapp.customer.CustomerDto;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public record GetCustomerResponse(CustomerDto customer) {
}
