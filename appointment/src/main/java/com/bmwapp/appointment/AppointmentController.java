package com.bmwapp.appointment;

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
    public void addApointment(@RequestBody AppointmentAddRequest appointmentAddRequest) {
        log.info("new appointment {}", appointmentAddRequest);
        appointmentService.addAppointment(appointmentAddRequest);
    }
}
