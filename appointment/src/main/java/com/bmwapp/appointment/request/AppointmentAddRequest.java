package com.bmwapp.appointment.request;

public record AppointmentAddRequest(Integer customerId, String appointmentName, String appointmentDate) {
}
