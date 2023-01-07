package com.bmwapp.appointment.service;

import com.bmwapp.appointment.context.ContextProvider;
import com.bmwapp.appointment.dto.AppointmentDto;
import com.bmwapp.appointment.exception.ResourceNotFoundException;
import com.bmwapp.appointment.model.Appointment;
import com.bmwapp.appointment.repository.AppointmentRepository;
import com.bmwapp.appointment.response.GetFlatResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public record AppointmentService(AppointmentRepository appointmentRepository,
                                 RestTemplate restTemplate, ModelMapper modelMapper, ContextProvider contextProvider) {

    public Appointment addAppointment(Appointment appointment) {
        appointment.setCustomerId(contextProvider.getLoggedUserId());
        GetFlatResponse response = restTemplate
                .getForObject("http://FLAT/api/v1/flats/{id}", GetFlatResponse.class, appointment.getFlatId());
        if(response.flatDto() == null) throw new ResourceNotFoundException(String.format("Flat with id %d not found", appointment.getFlatId()));
        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointment(Integer appointmentId) {
        Appointment appointment = appointmentRepository
                .findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Appointment with id %d not found", appointmentId)));
        return appointment;
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments;
    }

    public List<Appointment> getAllAppointmentsByCustomerId(String customerId) {
        List<Appointment> appointments = appointmentRepository.getAllByCustomerId(customerId);
        return appointments;
    }

    public Appointment updateAppointment(Appointment appointment) {
        if(appointment.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("ID of appointment is not set but is required."));
        }

        TypeMap<Appointment, Appointment> propertyMapper = modelMapper.getTypeMap(Appointment.class, Appointment.class);
        if(propertyMapper == null) propertyMapper = modelMapper.createTypeMap(Appointment.class, Appointment.class);
        Appointment oldAppointment = appointmentRepository
                .findById(appointment.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("Appointment with id %d not found", appointment.getId())));

        Provider<Appointment> appointmentProvider = p -> oldAppointment;
        propertyMapper.setProvider(appointmentProvider);

        Appointment updatedAppointment = modelMapper.map(appointment, Appointment.class);
        return appointmentRepository.save(updatedAppointment);
    }

    public void deleteAppointmentByIdAndCustomerId(Integer appointmentId, String customerId) {
        Appointment appointment = appointmentRepository
                .findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Appointment with id %d not found", appointmentId)));
        appointmentRepository.deleteByIdAndCustomerId(appointmentId, customerId);
    }

    public AppointmentDto convertToDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    public Appointment convertToEntity(AppointmentDto appointmentDto) {
        return modelMapper.map(appointmentDto, Appointment.class);
    }
}