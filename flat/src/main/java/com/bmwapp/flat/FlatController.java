package com.bmwapp.flat;
import com.bmwapp.flat.model.Address;
import com.bmwapp.flat.model.Flat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/flats")
public record FlatController(FlatService flatService) {

    @PostMapping
    public void addFlat(@Valid @RequestBody Flat flat) {
        log.info("new flat registration {}", flat);
        flatService.addFlat(flat);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Flat> getFlat(@PathVariable("id") Integer id) {
        log.info("get flat with id: {}", id);
        Flat flat = flatService.getFlat(id);
        return new ResponseEntity<>(flat, HttpStatus.OK);
    };

    @GetMapping()
    public ResponseEntity<List<Flat>> getSearchFlat(@RequestParam(value = "searchValue", required = false) String searchValue) {
        log.info("get all flats or with search value");
        List<Flat> flats;
        if(searchValue == null || searchValue.isEmpty()) flats = flatService.getAllFlats();
        else flats = flatService.getSearchFlat(searchValue);
        return new ResponseEntity<>(flats, HttpStatus.OK);
    };

    @PutMapping
    public void updateFlat(@Valid @RequestBody Flat flat) {
        log.info("update flat {}", flat);
        flatService.updateFlat(flat);
    }
}
