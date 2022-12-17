package com.bmwapp.flat.service;

import com.bmwapp.flat.dto.FlatDto;
import com.bmwapp.flat.exception.ResourceNotFoundException;
import com.bmwapp.flat.model.Flat;
import com.bmwapp.flat.repository.FlatRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public record FlatService(FlatRepository flatRepository, ModelMapper modelMapper) {

    public Flat addFlat(Flat flat) {
        Flat flatCreated = flatRepository.save(flat);
        return flatRepository.save(flat);
    }

    public Flat getFlat(Integer flatId) {
        Flat flat = flatRepository
                .findById(flatId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Flat with id %d not found", flatId)));
        return flat;
    }

    public List<FlatDto> getAllFlats() {
        List<Flat> flats = flatRepository.findAll();
        return flats.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FlatDto> getAllFlats(String searchValue) {
        List<Flat> flats = flatRepository.getAllFlats(searchValue.toLowerCase());
        return flats.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Flat updateFlat(Flat flat) {
        if(flat.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("ID of flat is not set but is required."));
        }

        TypeMap<Flat, Flat> propertyMapper = modelMapper.getTypeMap(Flat.class, Flat.class);
        if(propertyMapper == null) propertyMapper = modelMapper.createTypeMap(Flat.class, Flat.class);
        Flat oldFlat = flatRepository
                .findById(flat.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("Flat with id %d not found", flat.getId())));

        Provider<Flat> flatProvider = p -> oldFlat;
        propertyMapper.setProvider(flatProvider);

        Flat updatedFlat = modelMapper.map(flat, Flat.class);
        return flatRepository.save(updatedFlat);
    }

    public FlatDto convertToDto(Flat flat) {
        return modelMapper.map(flat, FlatDto.class);
    }

    public Flat convertToEntity(FlatDto flatDto) {
        return modelMapper.map(flatDto, Flat.class);
    }
}
