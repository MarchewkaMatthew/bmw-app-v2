package com.bmwapp.flat;

import com.bmwapp.flat.dto.FlatDto;
import com.bmwapp.flat.model.Flat;
import com.bmwapp.flat.repository.FlatRepository;
import com.bmwapp.flat.exception.ResourceNotFoundException;
import com.bmwapp.flat.request.FlatAddRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public record FlatService(FlatRepository flatRepository) {

    @Autowired
    private static ModelMapper modelMapper;

    public FlatDto addFlat(FlatAddRequest flatAddRequest) {
        Flat flat = convertToEntity(flatAddRequest.flatDto());
        Flat flatCreated = flatRepository.save(flat);
        return convertToDto(flatCreated);
    }

    public FlatDto getFlat(Integer id) {
        Flat flat = flatRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Flat with id %d not found", id)));
        return convertToDto(flat);
    }

    public List<FlatDto> getAllFlats() {
        List<Flat> flats = flatRepository.findAll();

        return flats.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<FlatDto> getSearchFlat(String searchValue) {
        List<Flat> flats = flatRepository.getSearchFlat(searchValue.toLowerCase());
        return flats.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void updateFlat(Flat updatedFlat) {
//        flatRepository
//                .findById(updatedFlat.getId())
//                .ifPresent(flat -> {
//                    flat.setFlatName(updatedFlat.getFlatName());
//                    flat.setAddress(updatedFlat.getAddress());
//                    flatRepository.save(flat);
//                });
            flatRepository.save(updatedFlat);
    }

    private FlatDto convertToDto(Flat flat) {
        return modelMapper.map(flat, FlatDto.class);
    }

    private Flat convertToEntity(FlatDto flatDto) {
        Flat flat = modelMapper.map(flatDto, Flat.class);

//        if (flatDto.getId() != null) {
//            Flat oldFlat = flatRepository.getById(flatDto.getId());
//            flat.setId(oldFlat.getId());//trzeba uzupelnic wszystkie stare dane, potrzebne do update.
//        }
        return flat;
    }
}
