package com.bmwapp.appointment;

import com.bmwapp.appointment.customer.GetCustomerResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public record AppointmentService(AppointmentRepository appointmentRepository, RestTemplate restTemplate) {
    public void addAppointment(AppointmentAddRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // "2023-11-23 00:00"
        LocalDateTime appointmentDate = LocalDateTime.parse(request.appointmentDate(), formatter);

        // SPRAWDZENIE CZY CUSTOMER ISTNIEJE
        try {
            GetCustomerResponse
                    response = restTemplate
                    .getForObject("http://localhost:8080/api/v1/customers/{id}", GetCustomerResponse.class,
                            request.customerId());

            Appointment appointment = Appointment.builder().customerId(request.customerId()).appointmentName(
                    request.appointmentName()).appointmentDate(appointmentDate).build();

            appointmentRepository.save(appointment);
        }
        catch (final HttpClientErrorException e) {
            System.out.println(e.getStatusCode());
            System.out.println(e.getResponseBodyAsString());

            throw e;
        }
    }
}