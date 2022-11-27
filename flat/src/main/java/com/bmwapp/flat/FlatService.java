package com.bmwapp.flat;

import com.bmwapp.flat.model.Flat;
import com.bmwapp.flat.repository.FlatRepository;
import com.bmwapp.flat.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record FlatService(FlatRepository flatRepository) {

    public void addFlat(Flat flat) {

        flatRepository.save(flat);
    }

    public Flat getFlat(Integer id) {
        return flatRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Flat with id %d not found", id)));
    }
}
