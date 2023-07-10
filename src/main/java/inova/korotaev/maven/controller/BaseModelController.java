package inova.korotaev.maven.controller;

import inova.korotaev.maven.dto.ThingDto;
import inova.korotaev.maven.model.SearchParamShell;
import inova.korotaev.maven.service.ThingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/base")
@RequiredArgsConstructor
public class BaseModelController {

    private final ThingService thingService;

    @PostMapping("/create-random")
    public ThingDto createRandom(@RequestBody ThingDto thingDto) {
            return thingService.save(thingDto);
    }

    @GetMapping("/{id}")
    public ThingDto getById(@PathVariable UUID id) {
        return thingService.getById(id);
    }

    @GetMapping("/search")
    public List<ThingDto> searchByRequest(@RequestBody SearchParamShell searchParamShell) {
        return thingService.findByRequest(searchParamShell);
    }
}