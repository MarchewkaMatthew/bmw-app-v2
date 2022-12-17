package com.bmwapp.flat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.Year;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
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
    private Double pricePerSquareMeter;
    private Double area;
    private Integer numberOfRooms;
    private Year constructionYear;
    private Integer floor;
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @Valid
    private Address address;

    public Double getPriceOfFlat(){
        return pricePerSquareMeter * area;
    }
}
