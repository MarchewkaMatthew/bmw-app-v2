package com.bmwapp.flat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import javax.persistence.*;

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
    private String address;
    private String district;
    private String city; //maybe we should create a separate model if needed
    private String country; //maybe we should create a separate model if needed
    private String postal_code;
    private Point location;
}
