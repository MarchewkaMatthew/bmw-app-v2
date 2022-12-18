package com.bmwapp.message.service;

import com.bmwapp.message.dto.MessageDto;
import com.bmwapp.message.exception.ResourceNotFoundException;
import com.bmwapp.message.model.Message;
import com.bmwapp.message.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public record MessageService(MessageRepository messageRepository, ModelMapper modelMapper) {

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

    public List<Message> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages;
    }

    public MessageDto convertToDto(Message message) {
        return modelMapper.map(message, MessageDto.class);
    }

    public Message convertToEntity(MessageDto messageDto) {
        return modelMapper.map(messageDto, Message.class);
    }
}