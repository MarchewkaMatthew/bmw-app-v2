package com.bmwapp.flat.dto;

import com.bmwapp.flat.model.Flat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
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
    private AddressDto address;

//    public static FlatDto FromDomain(@Valid Flat flat) {
////        return new FlatDto(flat.getId(), flat.getFlatName(),
////                flat.getPricePerSquareMeter(), flat.getArea(),
////                flat.getNumberOfRooms(), flat.getConstructionYear(), flat.getFloor(), flat.getIsActive(), AddressDto.FromDomain(flat.getAddress()));
//        FlatDto flatDto = modelMapper
//    }
}
