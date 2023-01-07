package com.bmwapp.appointment.controller;

import com.bmwapp.appointment.context.ContextProvider;
import com.bmwapp.appointment.model.Appointment;
import com.bmwapp.appointment.request.AppointmentAddRequest;
import com.bmwapp.appointment.request.AppointmentUpdateRequest;
import com.bmwapp.appointment.response.AppointmentResponse;
import com.bmwapp.appointment.response.AppointmentsResponse;
import com.bmwapp.appointment.service.AppointmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final ContextProvider contextProvider;

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping
    public AppointmentResponse addAppointment(@RequestBody AppointmentAddRequest appointmentAddRequest) {
        log.info("Add new appointment {}", appointmentAddRequest);
        Appointment appointment = appointmentService.convertToEntity(appointmentAddRequest.appointmentDto());
        return new AppointmentResponse(appointmentService.convertToDto(appointmentService.addAppointment(appointment)));
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping(path = "{id}")
    public AppointmentResponse getAppointment(@PathVariable("id") Integer appointmentId) {
        log.info("Get appointment with id: {}", appointmentId);
        Appointment appointment = appointmentService.getAppointment(appointmentId);
        return new AppointmentResponse(appointmentService.convertToDto(appointment));
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @GetMapping()
    public AppointmentsResponse getAllAppointments(@RequestParam(value = "customerId", required = false) String customerId) {
        log.info("Get all appointments or with customer id ");
        List<Appointment> appointments;
        if(customerId == null || customerId.isEmpty()) appointments = appointmentService.getAllAppointments();
        else appointments = appointmentService.getAllAppointmentsByCustomerId(customerId);

        return new AppointmentsResponse(appointments.stream()
                .map(appointmentService::convertToDto)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/forCustomer")
    public AppointmentsResponse getAllAppointmentsForCustomer() {
        log.info("Get all customers appointments");
        List<Appointment> appointments = appointmentService.getAllAppointmentsByCustomerId(contextProvider.getLoggedUserId());
        return new AppointmentsResponse(appointments.stream()
                .map(appointmentService::convertToDto)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'AGENT')")
    @PutMapping
    public AppointmentResponse updateAppointment(@RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        log.info("Update appointment {}", appointmentUpdateRequest);
        Appointment appointment = appointmentService.convertToEntity(appointmentUpdateRequest.appointmentDto());
        Appointment updatedAppointment = appointmentService.updateAppointment(appointment);
        return new AppointmentResponse(appointmentService.convertToDto(updatedAppointment));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") Integer appointmentId) {
        log.info("Delete appointment {}", appointmentId);
        appointmentService.deleteAppointmentByIdAndCustomerId(appointmentId, contextProvider.getLoggedUserId());
        return ResponseEntity.ok(String.format("Appointment with id %d deleted successfully", appointmentId));
    }
}
