package com.bmwapp.appointment;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public record GetCustomerResponse(CustomerDto customer) {
}
