package com.bmwapp.flat.controller;
import com.bmwapp.flat.dto.FlatDto;
import com.bmwapp.flat.model.Flat;
import com.bmwapp.flat.request.FlatAddRequest;
import com.bmwapp.flat.request.FlatUpdateRequest;
import com.bmwapp.flat.response.GetFlatResponse;
import com.bmwapp.flat.response.GetFlatsResponse;
import com.bmwapp.flat.service.FlatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/flats")
@AllArgsConstructor
public class FlatController {
    private final FlatService flatService;

    @PreAuthorize("hasAuthority('AGENT')")
    @PostMapping
    public FlatDto addFlat(@RequestBody FlatAddRequest flatAddRequest) {
        log.info("new flat registration {}", flatAddRequest);
        Flat flat = flatService.convertToEntity(flatAddRequest.flatDto());
        return flatService.convertToDto(flatService.addFlat(flat));
    }

    @GetMapping(path = "{id}")
    public GetFlatResponse getFlat(@PathVariable("id") Integer flatId) {
        log.info("get flat with id: {}", flatId);
        Flat flat = flatService.getFlat(flatId);
        return new GetFlatResponse(flatService.convertToDto(flat));
    }

    @GetMapping()
    public GetFlatsResponse getAllFlats(@RequestParam(value = "searchValue", required = false) String searchValue) {
        log.info("get all flats or with search value");
        List<FlatDto> flats;
        if(searchValue == null || searchValue.isEmpty()) flats = flatService.getAllFlats();
        else flats = flatService.getAllFlats(searchValue);
        return new GetFlatsResponse(flats);
    }

    @PreAuthorize("hasAuthority('AGENT')")
    @PutMapping
    public FlatDto updateFlat(@RequestBody FlatUpdateRequest flatUpdateRequest) {
        log.info("update flat {}", flatUpdateRequest);

        Flat flat = flatService.convertToEntity(flatUpdateRequest.flatDto());
        return flatService.convertToDto(flatService.updateFlat(flat));
    }
}
