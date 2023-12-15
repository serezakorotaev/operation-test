package inova.korotaev.maven.controller;

import inova.korotaev.maven.dto.ThingDto;
import inova.korotaev.maven.service.ThingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sergkorot.dynamic.model.shell.CommonOperationShell;
import ru.sergkorot.dynamic.model.shell.MultipleOperationShell;

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
    public List<ThingDto> searchByRequest(@RequestBody CommonOperationShell searchParamShell) {
        return thingService.findByBaseRequest(searchParamShell);
    }

    @GetMapping("/complex-search")
    public List<ThingDto> searchByRequest(@RequestBody MultipleOperationShell searchParamShell) {
        return thingService.findByComplexRequest(searchParamShell);
    }

    @GetMapping("/query")
    public List<ThingDto> searchByRequest(@RequestParam String query,
                                          @RequestParam Integer limit,
                                          @RequestParam Integer offset,
                                          @RequestParam String sortBy) {
        return thingService.findByQuery(query, limit, offset, sortBy);
    }
}
