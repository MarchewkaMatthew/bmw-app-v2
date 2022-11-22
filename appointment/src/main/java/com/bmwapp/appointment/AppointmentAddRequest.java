package com.bmwapp.appointment;

public record AppointmentAddRequest(Integer customerId, String appointmentName, String appointmentDate) {
}
