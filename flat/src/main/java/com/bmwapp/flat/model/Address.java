package com.bmwapp.flat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @SequenceGenerator(
            name = "address_id_sequence",
            sequenceName = "address_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_id_sequence"
    )
    private Integer id;
    @NotBlank(message = "Street is mandatory")
    private String street;
    @NotBlank(message = "District is mandatory")
    private String district;
    @NotBlank(message = "City is mandatory")
    private String city;
    @NotBlank(message = "Country is mandatory")
    private String country;
    @NotBlank(message = "Postal code is mandatory")
    private String postal_code;
    private Point location;
}
