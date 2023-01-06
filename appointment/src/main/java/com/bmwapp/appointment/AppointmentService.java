package com.bmwapp.appointment;

import com.bmwapp.appointment.context.ContextProvider;
import com.bmwapp.appointment.dto.AppointmentDto;
import com.bmwapp.appointment.model.Appointment;
import com.bmwapp.appointment.repository.AppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Slf4j
@Service
public record AppointmentService(AppointmentRepository appointmentRepository, RestTemplate restTemplate,
                                 ModelMapper modelMapper) {

    static ContextProvider contextProvider;
    public Appointment addAppointment(Appointment appointment) {
        if(!contextProvider.getAuthenticationToken().isAuthenticated()) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User must be be authenticated!");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // "2023-11-23 00:00"
        LocalDateTime appointmentDate = LocalDateTime.parse(appointment.getAppointmentDate().toString(), formatter);

        appointment.setAppointmentDate(appointmentDate);
        appointment.setCustomerId(contextProvider.getLoggedUserId());

        return appointmentRepository.save(appointment);
    }


    public AppointmentDto convertToDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    public Appointment convertToEntity(AppointmentDto appointmentDto) {
        return modelMapper.map(appointmentDto, Appointment.class);
    }
}