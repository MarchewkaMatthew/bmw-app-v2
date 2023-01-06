package com.bmwapp.appointment;

import com.bmwapp.appointment.customer.GetCustomerResponse;
import com.bmwapp.appointment.model.Appointment;
import com.bmwapp.appointment.repository.AppointmentRepository;
import com.bmwapp.appointment.request.AppointmentAddRequest;
import org.springframework.http.HttpStatus;
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
                    .getForObject("http://CUSTOMER/api/v1/customers/{id}", GetCustomerResponse.class,
                            request.customerId());

            Appointment appointment = Appointment.builder().customerId(request.customerId()).appointmentName(
                    request.appointmentName()).appointmentDate(appointmentDate).build();

            appointmentRepository.save(appointment);
        }
        catch (final HttpClientErrorException e) {
            // TODO: Check why forwarding the throw e is not working
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Consumer with id %d not found", request.customerId()));
        }
    }
}
