package com.bmwapp.customer.address;

import org.springframework.data.geo.Point;

import java.io.Serializable;

public class AddressDto implements Serializable {
    private Integer id;
    private String street;
    private String district;
    private String city;
    private String country;
    private String postal_code;
    private Point location;
}
