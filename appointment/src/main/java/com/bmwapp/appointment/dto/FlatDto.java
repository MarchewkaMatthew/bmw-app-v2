package com.bmwapp.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlatDto implements Serializable {
    private Integer id;
    private String flatName;
    private Double pricePerSquareMeter;
    private Double area;
    private Integer numberOfRooms;
    private Year constructionYear;
    private Integer floor;
    private Boolean isActive;
}