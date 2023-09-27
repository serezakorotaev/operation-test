package inova.korotaev.maven.controller;

import inova.korotaev.maven.dto.JsonDto;
import inova.korotaev.maven.service.JsonBService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sergkorot.dynamic.model.shell.CommonOperationShell;
import ru.sergkorot.dynamic.model.shell.MultipleOperationShell;

import java.util.List;

@RestController
@RequestMapping("/jsonb")
@RequiredArgsConstructor
public class JsonBController {

    private final JsonBService jsonBService;

    @GetMapping("/search")
    public List<JsonDto> searchByRequest(@RequestBody CommonOperationShell searchParamShell, @RequestParam String expression) {
        System.out.println(expression);
        return jsonBService.findByBaseRequest(searchParamShell);
    }

    @GetMapping("/complex-search")
    public List<JsonDto> searchByRequest(@RequestBody MultipleOperationShell searchParamShell) {
        return jsonBService.findByComplexRequest(searchParamShell);
    }

    @PostMapping("/save")
    public JsonDto save(@RequestBody JsonDto jsonDto) {
        return jsonBService.save(jsonDto);
    }
}
