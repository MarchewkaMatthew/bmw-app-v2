package com.bmwapp.message;

import com.bmwapp.message.dto.MessageDto;
import com.bmwapp.message.response.GetMessagesResponse;
import com.bmwapp.message.service.MessageService;
import com.bmwapp.message.model.Message;
import com.bmwapp.message.request.MessageAddRequest;
import com.bmwapp.message.response.GetMessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/messages")
public record MessageController(MessageService messageService) {

    @PostMapping
    public MessageDto addMessage(@RequestBody MessageAddRequest messageAddRequest) {
        log.info("new message registration {}", messageAddRequest);
        Message message = messageService.convertToEntity(messageAddRequest.messageDto());
        return messageService.convertToDto(messageService.addMessage(message));
    }

    @GetMapping(path = "{id}")
    public GetMessageResponse getMessage(@PathVariable("id") Integer messageId) {
        log.info("get message with id: {}", messageId);
        Message message = messageService.getMessage(messageId);
        return new GetMessageResponse(messageService.convertToDto(message));
    }

    @GetMapping()
    public GetMessagesResponse getAllMessages() {
        log.info("get all messages ");
        List<MessageDto> messages;
        messages = messageService.getAllMessages().stream()
                .map(messageService::convertToDto)
                .collect(Collectors.toList());
        return new GetMessagesResponse(messages);
    }

}
