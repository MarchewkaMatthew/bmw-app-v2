package com.bmwapp.customer.response;

import com.bmwapp.customer.CustomerDto;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
public record GetAllCustomersResponse(List<CustomerDto> customerDtoList) {
}
