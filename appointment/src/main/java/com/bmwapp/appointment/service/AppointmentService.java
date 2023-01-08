package com.bmwapp.appointment.service;

import com.bmwapp.appointment.context.ContextProvider;
import com.bmwapp.appointment.dto.AppointmentDto;
import com.bmwapp.appointment.exception.ResourceNotFoundException;
import com.bmwapp.appointment.model.Appointment;
import com.bmwapp.appointment.repository.AppointmentRepository;
import com.bmwapp.appointment.response.GetFlatResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;
    private final ContextProvider contextProvider;

    public Appointment addAppointment(Appointment appointment) {
        appointment.setCustomerId(contextProvider.getLoggedUserId());
        GetFlatResponse response = restTemplate
                .getForObject("http://FLAT/api/v1/flats/{id}", GetFlatResponse.class, appointment.getFlatId());
        if (response != null && response.flatDto() == null)
            throw new ResourceNotFoundException(String.format("Flat with id %d not found", appointment.getFlatId()));
        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointment(Integer appointmentId) {
        return appointmentRepository
                .findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Appointment with id %d not found", appointmentId)));
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAllAppointmentsByCustomerId(String customerId) {
        return appointmentRepository.getAllByCustomerId(customerId);
    }

    public Appointment updateAppointment(Appointment appointment) {
        if(appointment.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "ID of appointment is not set but is required.");
        }

        TypeMap<Appointment, Appointment> propertyMapper = modelMapper.getTypeMap(Appointment.class, Appointment.class);
        if(modelMapper.getTypeMap(Appointment.class, Appointment.class) == null) propertyMapper = modelMapper.createTypeMap(Appointment.class, Appointment.class);

        Appointment oldAppointment = appointmentRepository
                .findById(appointment.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("Appointment with id %d not found", appointment.getId())));

        Provider<Appointment> appointmentProvider = p -> oldAppointment;
        propertyMapper.setProvider(appointmentProvider);

        if(contextProvider.userHasRole("CUSTOMER")){
            modelMapper.getTypeMap(Appointment.class, Appointment.class)
                    .addMappings(mapper ->mapper.skip(Appointment::setCustomerId))
                    .addMappings(mapper ->mapper.skip(Appointment::setFlatId))
                    .addMappings(mapper ->mapper.skip(Appointment::setAppointmentName));
        } else {
            modelMapper.getTypeMap(Appointment.class, Appointment.class)
                    .addMappings(mapper ->mapper.map(Appointment::getCustomerId, Appointment::setCustomerId))
                    .addMappings(mapper ->mapper.map(Appointment::getFlatId, Appointment::setFlatId))
                    .addMappings(mapper ->mapper.map(Appointment::getAppointmentName, Appointment::setAppointmentName));
        }

        Appointment updatedAppointment = modelMapper.map(appointment, Appointment.class);
        return appointmentRepository.save(updatedAppointment);
    }

    public void deleteAppointment(Integer appointmentId) {
        if (!appointmentRepository.findById(appointmentId).isPresent() || (contextProvider.userHasRole("CUSTOMER")
                && !appointmentRepository.findOneByIdAndCustomerId(appointmentId, contextProvider.getLoggedUserId()).isPresent())) {
            throw new ResourceNotFoundException(String.format("Appointment not found"));
        }

        if(contextProvider.userHasRole("CUSTOMER")){
            appointmentRepository.deleteByIdAndCustomerId(appointmentId, contextProvider.getLoggedUserId());
        } else {
            appointmentRepository.deleteById(appointmentId);
        }
    }

    public void deleteAllAppointments() {
        appointmentRepository.deleteAll();
    }

    public AppointmentDto convertToDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDto.class);
    }

    public Appointment convertToEntity(AppointmentDto appointmentDto) {
        return modelMapper.map(appointmentDto, Appointment.class);
    }
}