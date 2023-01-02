package com.bmwapp.message.response;

import com.bmwapp.message.dto.MessageDto;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
public record GetMessagesResponse(List<MessageDto> messageDtoList) {
}