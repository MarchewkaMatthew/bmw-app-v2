package com.bmwapp.flat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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

    @Column(unique=true)
    @NotBlank(message = "Name of flat is mandatory")
    private String flatName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @Valid
    private Address address;

//    private boolean isActive; ??
//    we have to think about what attributes we need here. owner?
}
