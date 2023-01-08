package com.bmwapp.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto implements Serializable {
    private Integer id;
    private String customerId;
    private String appointmentName;
    private String appointmentDate;
    private Integer flatId;
    private String status; //NEW, ACCEPTED, REJECTED, CANCELED ???
}
