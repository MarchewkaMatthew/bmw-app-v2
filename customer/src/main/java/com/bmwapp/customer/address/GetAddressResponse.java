package com.bmwapp.customer.address;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public record GetAddressResponse(AddressDto addressDto) {
}
