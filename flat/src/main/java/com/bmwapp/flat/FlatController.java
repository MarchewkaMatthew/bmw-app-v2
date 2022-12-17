package com.bmwapp.flat;
import com.bmwapp.flat.dto.FlatDto;
import com.bmwapp.flat.model.Flat;
import com.bmwapp.flat.request.FlatAddRequest;
import com.bmwapp.flat.response.GetFlatResponse;
import com.bmwapp.flat.response.GetFlatsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/flats")
public record FlatController(FlatService flatService) {

    @PostMapping
    public FlatDto addFlat(@RequestBody FlatAddRequest flatAddRequest) {
        log.info("new flat registration {}", flatAddRequest);
        return flatService.addFlat(flatAddRequest);
    }

    @GetMapping(path = "{id}")
    public GetFlatResponse getFlat(@PathVariable("id") Integer id) {
        log.info("get flat with id: {}", id);
        FlatDto flatDto = flatService.getFlat(id);
        return new GetFlatResponse(flatDto);
    }

    @GetMapping()
    public GetFlatsResponse getSearchFlat(@RequestParam(value = "searchValue", required = false) String searchValue) {
        log.info("get all flats or with search value");
        List<FlatDto> flats;
        if(searchValue == null || searchValue.isEmpty()) flats = flatService.getAllFlats();
        else flats = flatService.getSearchFlat(searchValue);
        return new GetFlatsResponse(flats);
    }

    @PutMapping
    public void updateFlat(@RequestBody Flat flat) {
        log.info("update flat {}", flat);
        flatService.updateFlat(flat);
    }
}
