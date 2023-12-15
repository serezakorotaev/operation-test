package inova.korotaev.maven.controller;

import inova.korotaev.maven.dto.MongoDto;
import inova.korotaev.maven.service.MongoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sergkorot.dynamic.model.shell.CommonOperationShell;
import ru.sergkorot.dynamic.model.shell.MultipleOperationShell;

import java.util.List;

@RestController
@RequestMapping("/mongo")
@RequiredArgsConstructor
public class MongoController {

    private final MongoService service;

    @PostMapping("/save")
    public MongoDto save(@RequestBody MongoDto mongoDto) {
        return service.save(mongoDto);
    }

    @GetMapping("/base-search")
    public List<MongoDto> search(@RequestBody CommonOperationShell shell) {
        return service.findBase(shell);
    }

    @GetMapping("/complex-search")
    public List<MongoDto> searchComplex(@RequestBody MultipleOperationShell shell) {
        return service.findComplex(shell);
    }


    @GetMapping("/query")
    public List<MongoDto> searchByRequest(@RequestParam String query,
                                          @RequestParam Integer limit,
                                          @RequestParam Integer offset,
                                          @RequestParam String sortBy) {
        return service.findByQuery(query, limit, offset, sortBy);
    }}
