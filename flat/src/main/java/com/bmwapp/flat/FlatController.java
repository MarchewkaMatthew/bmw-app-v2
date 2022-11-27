package com.bmwapp.flat;
import com.bmwapp.flat.model.Flat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/flats")
public record FlatController(FlatService flatService) {

    @PostMapping
    public void addFlat(@RequestBody Flat flat) {
        log.info("new flat registration {}", flat);
        flatService.addFlat(flat);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Flat> getFlat(@PathVariable("id") Integer id) {
        log.info("get flat with id: {}", id);

        Flat flat = flatService.getFlat(id);
        return new ResponseEntity<>(flat, HttpStatus.OK);
    };
}
