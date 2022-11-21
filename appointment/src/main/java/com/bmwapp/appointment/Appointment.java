package com.bmwapp.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
    @SequenceGenerator(
            name = "appointment_id_sequence",
            sequenceName = "appointment_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_id_sequence"
    )
    private Integer id;
    private Integer customerId;
    private String appointmentName;
    private LocalDateTime appointmentDate;
}
