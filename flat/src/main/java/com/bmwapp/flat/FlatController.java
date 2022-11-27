package com.bmwapp.flat;
import com.bmwapp.flat.model.Flat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/flats")
public record FlatController(FlatService flatService) {

    @PostMapping
    public void registerCustomer(@RequestBody Flat flat) {
        log.info("new flat registration {}", flat);
        flatService.addFlat(flat);
    }

//    @GetMapping(path = "{id}")
//    public GetFlatResponse getCustomer(@PathVariable("id") Integer id) {
//        log.info("get customer with id: {}", id);
//
//        Flat flat = flatService.getFlat(id);
//        return new GetFlatResponse(FlatDto.FromDomain(flat));
//    };
}
