package com.bmwapp.flat.dto;

import com.bmwapp.flat.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto implements Serializable {
    private Integer id;
    private String street;
    private String district;
    private String city;
    private String country;
    private String postal_code;
    private Point location;

    public static AddressDto FromDomain(Address address) {
        return new AddressDto(address.getId(), address.getStreet(),
                address.getDistrict(), address.getCity(),
                address.getCountry(), address.getPostal_code(), address.getLocation());
    }
}
