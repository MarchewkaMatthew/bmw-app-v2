package com.bmwapp.appointment;

import com.bmwapp.appointment.dto.AppointmentDto;
import com.bmwapp.appointment.model.Appointment;
import com.bmwapp.appointment.request.AppointmentAddRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/appointments")
public record AppointmentController(AppointmentService appointmentService) {

    @PostMapping
    public AppointmentDto addAppointment(@RequestBody AppointmentAddRequest appointmentAddRequest) {
        log.info("Add new Appointment {}", appointmentAddRequest);
        Appointment appointment = appointmentService.convertToEntity(appointmentAddRequest.appointmentDto());
        return appointmentService.convertToDto(appointmentService.addAppointment(appointment));
    }
}
