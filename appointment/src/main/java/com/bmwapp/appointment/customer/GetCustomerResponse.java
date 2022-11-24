package com.bmwapp.appointment.customer;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public record GetCustomerResponse(CustomerDto customer) {
}
