package com.bmwapp.appointment.controller;

import com.bmwapp.appointment.context.ContextProvider;
import com.bmwapp.appointment.exception.PermissionSecurityException;
import com.bmwapp.appointment.exception.ResourceNotFoundException;
import com.bmwapp.appointment.model.Appointment;
import com.bmwapp.appointment.request.AppointmentAddRequest;
import com.bmwapp.appointment.request.AppointmentUpdateRequest;
import com.bmwapp.appointment.response.AppointmentResponse;
import com.bmwapp.appointment.response.AppointmentsResponse;
import com.bmwapp.appointment.service.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/appointments")
public record AppointmentController(AppointmentService appointmentService, ContextProvider contextProvider) {

    @PostMapping
    public AppointmentResponse addAppointment(@RequestBody AppointmentAddRequest appointmentAddRequest) {
        contextProvider.requiredAnyRoles("CUSTOMER", "AGENT");
        log.info("Add new appointment {}", appointmentAddRequest);
        Appointment appointment = appointmentService.convertToEntity(appointmentAddRequest.appointmentDto());
        return new AppointmentResponse(appointmentService.convertToDto(appointmentService.addAppointment(appointment)));
    }

    @GetMapping(path = "{id}")
    public AppointmentResponse getAppointment(@PathVariable("id") Integer appointmentId) {
        contextProvider.requiredRole("AGENT");
        log.info("Get appointment with id: {}", appointmentId);
        Appointment appointment = appointmentService.getAppointment(appointmentId);
        return new AppointmentResponse(appointmentService.convertToDto(appointment));
    }

    @GetMapping()
    public AppointmentsResponse getAllAppointments(@RequestParam(value = "customerId", required = false) String customerId) {
        contextProvider.requiredRole("AGENT");
        log.info("Get all appointments or with customer id ");
        List<Appointment> appointments;
        if(customerId == null || customerId.isEmpty()) appointments = appointmentService.getAllAppointments();
        else appointments = appointmentService.getAllAppointmentsByCustomerId(customerId);

        return new AppointmentsResponse(appointments.stream()
                .map(appointmentService::convertToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/forCustomer")
    public AppointmentsResponse getAllAppointmentsForCustomer() {
        contextProvider.requiredAnyRoles("CUSTOMER", "AGENT");
        log.info("Get all customers appointments");
        List<Appointment> appointments = appointmentService.getAllAppointmentsByCustomerId(contextProvider.getLoggedUserId());
        return new AppointmentsResponse(appointments.stream()
                .map(appointmentService::convertToDto)
                .collect(Collectors.toList()));
    }

    @PutMapping
    public AppointmentResponse updateAppointment(@RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        contextProvider.requiredAnyRoles("CUSTOMER", "AGENT");
        log.info("Update appointment {}", appointmentUpdateRequest);
        Appointment appointment = appointmentService.convertToEntity(appointmentUpdateRequest.appointmentDto());
        Appointment updatedAppointment = appointmentService.updateAppointment(appointment);
        return new AppointmentResponse(appointmentService.convertToDto(updatedAppointment));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") Integer appointmentId) {
        contextProvider.requiredAnyRoles("CUSTOMER", "AGENT");
        log.info("Delete appointment {}", appointmentId);
        appointmentService.deleteAppointmentById(appointmentId);
        return ResponseEntity.ok(String.format("Appointment with id %d deleted successfully", appointmentId));
    }
}
