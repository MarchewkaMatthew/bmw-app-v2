package com.bmwapp.flat;

import com.bmwapp.flat.model.Flat;
import com.bmwapp.flat.repository.FlatRepository;
import com.bmwapp.flat.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Flat> getAllFlats() {
        return flatRepository.findAll();
    }

    public List<Flat> getSearchFlat(String searchValue) {
        return flatRepository.getSearchFlat(searchValue.toLowerCase());
    }

    public void updateFlat(Flat updatedFlat) {
        flatRepository
                .findById(updatedFlat.getId())
                .ifPresent(flat -> {
                    flat.setFlatName(updatedFlat.getFlatName());
                    flat.setAddress(updatedFlat.getAddress());
                    flatRepository.save(flat);
                });
//        flatRepository.save(updatedFlat) <-- można też od razu, ale wyżej lepiej sprawdzić czy istnieje entity zamiast dodawać jeśli nie istnieje itp.
    }
}
