package com.bmwapp.appointment;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public record AppointmentService(AppointmentRepository appointmentRepository) {
    public void addAppointment(AppointmentAddRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime appointmentDate = LocalDateTime.parse(request.appointmentDate(), formatter);

        // TODO: Check does customer exist

        Appointment appointment = Appointment.builder().customerId(request.customerId()).appointmentName(
                request.appointmentName()).appointmentDate(appointmentDate).build();

        appointmentRepository.save(appointment);
    }
}
