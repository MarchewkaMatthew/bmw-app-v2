package com.bmwapp.flat.response;

import com.bmwapp.flat.dto.FlatDto;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
public record GetFlatsResponse(List<FlatDto> flatDtoList) {
}
