package com.bmwapp.message.controller;

import com.bmwapp.message.context.ContextProvider;
import com.bmwapp.message.dto.MessageDto;
import com.bmwapp.message.response.GetMessagesResponse;
import com.bmwapp.message.service.MessageService;
import com.bmwapp.message.model.Message;
import com.bmwapp.message.request.MessageAddRequest;
import com.bmwapp.message.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/messages")
public record MessageController(MessageService messageService, ContextProvider contextProvider) {

    @PostMapping
    public MessageResponse addMessage(@RequestBody MessageAddRequest messageAddRequest) {
        log.info("Add new message {}", messageAddRequest);
        Message message = messageService.convertToEntity(messageAddRequest.messageDto());
        return new MessageResponse(messageService.convertToDto(messageService.addMessage(message)));
    }

    @GetMapping(path = "{id}")
    public MessageResponse getMessage(@PathVariable("id") Integer messageId) {
        log.info("Get message with id: {}", messageId);
        Message message = messageService.getMessage(messageId);
        return new MessageResponse(messageService.convertToDto(message));
    }

    @GetMapping()
    public GetMessagesResponse getAllMessages() {
        log.info("Get all messages");
        List<MessageDto> messages = messageService.getAllMessages().stream()
                .map(messageService::convertToDto)
                .collect(Collectors.toList());
        return new GetMessagesResponse(messages);
    }

}
