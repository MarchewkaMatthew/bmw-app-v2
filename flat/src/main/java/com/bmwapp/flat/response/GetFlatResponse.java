package com.bmwapp.flat.response;

import com.bmwapp.flat.dto.FlatDto;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public record GetFlatResponse(FlatDto flatDto) {
}
