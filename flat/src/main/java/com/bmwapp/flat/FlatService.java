package com.bmwapp.flat;

import com.bmwapp.flat.model.Flat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public record FlatService(FlatRepository flatRepository) {

    public void addFlat(Flat flat) {

        flatRepository.save(flat);
    }

    public Flat getFlat(Integer id) {
        return flatRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Flat with id %d not found", id)));
    }
}
