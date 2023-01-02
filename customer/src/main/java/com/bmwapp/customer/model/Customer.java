package com.bmwapp.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "customer_id")
    private List<Investment> investments = new ArrayList<>();

    public void addInvestment(Investment investment){
        if(!investments.contains(investment)) investments.add(investment);
    }
}
