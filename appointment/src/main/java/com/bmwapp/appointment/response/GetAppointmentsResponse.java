package com.bmwapp.appointment.response;

import com.bmwapp.appointment.dto.AppointmentDto;

import java.util.List;

public record GetAppointmentsResponse(List<AppointmentDto> appointmentDtoList) {
}
