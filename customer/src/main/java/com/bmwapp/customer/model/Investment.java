package com.bmwapp.customer.model;

import com.bmwapp.customer.flat.FlatDto;
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
public class Investment {

    @Id
    @SequenceGenerator(
            name = "investment_id_sequence",
            sequenceName = "investment_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "investment_id_sequence"
    )
    private Integer id;

    private String status;

}
