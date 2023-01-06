package com.bmwapp.appointment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
    @Length(max = 36)
    private String customerId; //String, ponieważ jest to id z keycloak które ma w bazie varchar(36)
    private String appointmentName;
    private LocalDateTime appointmentDate;
    private Integer flatId;
}
