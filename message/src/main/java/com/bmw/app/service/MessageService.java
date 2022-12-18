package com.bmw.app.service;

import com.bmw.app.dto.MessageDto;
import com.bmw.app.repository.MessageRepository;
import com.bmw.app.exception.ResourceNotFoundException;
import com.bmw.app.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public record MessageService(MessageRepository messageRepository) {

    public Message addMessage(Message message) {
        Message messageCreated = messageRepository.save(message);
        return messageRepository.save(message);
    }

    public Message getMessage(Integer messageId) {
        Message message = messageRepository
                .findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Message with id %d not found", messageId)));
        return message;
    }

    public List<MessageDto> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MessageDto convertToDto(Message message) {
        return modelMapper.map(message, MessageDto.class);
    }

    public Message convertToEntity(MessageDto messageDto) {
        return modelMapper.map(messageDto, Message.class);
    }
}