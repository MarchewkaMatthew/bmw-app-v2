package com.bmwapp.appointment;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public record AppointmentService(AppointmentRepository appointmentRepository, RestTemplate restTemplate) {
    public void addAppointment(AppointmentAddRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // "2023-11-23 00:00"
        LocalDateTime appointmentDate = LocalDateTime.parse(request.appointmentDate(), formatter);

        GetCustomerResponse response = restTemplate.getForObject("http:localhost:8080/api/v1/customer/{id}", GetCustomerResponse.class, request.customerId());

        if(response.customer() != null) {
            Appointment appointment = Appointment.builder().customerId(request.customerId()).appointmentName(
                    request.appointmentName()).appointmentDate(appointmentDate).build();

            appointmentRepository.save(appointment);
        }
    }
}
