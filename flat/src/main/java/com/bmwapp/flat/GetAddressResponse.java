package com.bmwapp.flat;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public record GetAddressResponse(AddressDto addressDto) {
}
