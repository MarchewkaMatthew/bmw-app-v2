package com.bmwapp.message.response;

import com.bmwapp.message.dto.MessageDto;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public record GetMessageResponse(MessageDto messageDto) {
}