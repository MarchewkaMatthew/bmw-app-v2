package com.bmwapp.flat.response;

import com.bmwapp.flat.dto.AddressDto;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public record GetAddressResponse(AddressDto addressDto) {
}
