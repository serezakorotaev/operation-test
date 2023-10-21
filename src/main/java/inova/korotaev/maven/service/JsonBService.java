package inova.korotaev.maven.service;

import inova.korotaev.maven.dto.JsonDto;
import inova.korotaev.maven.mapper.JsonMapper;
import inova.korotaev.maven.model.JsonBEntity;
import inova.korotaev.maven.repository.JsonBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.sergkorot.dynamic.model.paging.PageRequestWithOffset;
import ru.sergkorot.dynamic.model.shell.CommonOperationShell;
import ru.sergkorot.dynamic.model.shell.MultipleOperationShell;
import ru.sergkorot.dynamic.operation.SpecificationOperationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JsonBService {

    private final JsonBRepository jsonBRepository;
    private final JsonMapper jsonMapper;
    private final SpecificationOperationService<JsonBEntity> operationService;

    private final static List<String> SORTED_FIELDS = List.of("id", "value");

    public List<JsonDto> findByBaseRequest(CommonOperationShell searchParamShell) {
        PageRequestWithOffset pageRequestWithOffset = operationService.buildPageSettings(searchParamShell.getPageAttribute(), SORTED_FIELDS);
        Specification<JsonBEntity> specification = operationService.buildBaseByParams(searchParamShell.getBaseSearchParams(), searchParamShell.getGlue());
        Page<JsonBEntity> jsonBEntities = jsonBRepository.findAll(specification, pageRequestWithOffset);
        return jsonMapper.toJsonDtos(jsonBEntities.getContent());
    }

    public List<JsonDto> findByComplexRequest(MultipleOperationShell searchParamShell) {
        PageRequestWithOffset pageRequestWithOffset = operationService.buildPageSettings(searchParamShell.getPageAttribute(), SORTED_FIELDS);
        Specification<JsonBEntity> specification = operationService.buildComplexByParams(searchParamShell.getSearch(), searchParamShell.getExternalGlue());
        Page<JsonBEntity> jsonBEntities = jsonBRepository.findAll(specification, pageRequestWithOffset);
        return jsonMapper.toJsonDtos(jsonBEntities.getContent());
    }

    public JsonDto save(JsonDto jsonDto) {
        JsonBEntity bEntity = jsonMapper.toJsonBEntity(jsonDto);
        return jsonMapper.toJsonDto(jsonBRepository.save(bEntity));
    }
}
