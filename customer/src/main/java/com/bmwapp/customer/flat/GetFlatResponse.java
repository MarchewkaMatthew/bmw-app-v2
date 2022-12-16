package com.bmwapp.customer.flat;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public record GetFlatResponse(FlatDto flatDto) {
}
