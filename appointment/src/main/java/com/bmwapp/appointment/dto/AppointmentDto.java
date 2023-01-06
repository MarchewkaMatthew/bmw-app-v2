package com.bmwapp.appointment.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AppointmentDto implements Serializable {
    private Integer id;
    private Integer customerId;
    private String appointmentName;
    private LocalDateTime appointmentDate;
    private Integer flatId;
}
