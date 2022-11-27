package com.bmwapp.flat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flat {
    @Id
    @SequenceGenerator(
            name = "flat_id_sequence",
            sequenceName = "flat_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flat_id_sequence"
    )
    private Integer id;
    private String flatName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    //we have to think about what attributes we need here. owner?
}
