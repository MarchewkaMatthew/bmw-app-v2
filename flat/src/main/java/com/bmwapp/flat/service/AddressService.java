package com.bmwapp.flat.service;

import com.bmwapp.flat.dto.AddressDto;
import com.bmwapp.flat.dto.FlatDto;
import com.bmwapp.flat.model.Address;
import com.bmwapp.flat.model.Flat;
import com.bmwapp.flat.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public record AddressService(AddressRepository addressRepository) {

    private static ModelMapper modelMapper = new ModelMapper();

    private AddressDto convertToDto(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }

    private Address convertToEntity(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        return address;
    }
}
