package inova.korotaev.maven.controller;

import inova.korotaev.maven.dto.JsonDto;
import inova.korotaev.maven.model.shell.CommonOperationShell;
import inova.korotaev.maven.model.shell.MultipleOperationShell;
import inova.korotaev.maven.service.JsonBService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jsonb")
@RequiredArgsConstructor
public class JsonBController {

    private final JsonBService jsonBService;

    @GetMapping("/search")
    public List<JsonDto> searchByRequest(@RequestBody CommonOperationShell searchParamShell) {
        return jsonBService.findByBaseRequest(searchParamShell);
    }

    @GetMapping("/complex-search")
    public List<JsonDto> searchByRequest(@RequestBody MultipleOperationShell searchParamShell) {
        return jsonBService.findByComplexRequest(searchParamShell);
    }
}
