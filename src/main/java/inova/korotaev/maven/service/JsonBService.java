package inova.korotaev.maven.service;

import inova.korotaev.maven.dto.JsonDto;
import inova.korotaev.maven.mapper.JsonMapper;
import inova.korotaev.maven.model.JsonBEntity;
import inova.korotaev.maven.model.paging.PageRequestWithOffset;
import inova.korotaev.maven.model.shell.CommonOperationShell;
import inova.korotaev.maven.model.shell.MultipleOperationShell;
import inova.korotaev.maven.operation.OperationService;
import inova.korotaev.maven.repository.JsonBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JsonBService {

    private final JsonBRepository jsonBRepository;
    private final JsonMapper jsonMapper;
    private final OperationService<JsonBEntity> operationService;

    private final static List<String> SORTED_FIELDS = List.of("id", "value");

    public List<JsonDto> findByBaseRequest(CommonOperationShell searchParamShell) {
        PageRequestWithOffset pageRequestWithOffset = operationService.buildPageSettings(searchParamShell.getPageAttribute(), SORTED_FIELDS);
        Specification<JsonBEntity> specification = operationService.buildBaseSpecificationByParams(searchParamShell.getBaseSearchParams(), searchParamShell.getGlue());
        Page<JsonBEntity> jsonBEntities = jsonBRepository.findAll(specification, pageRequestWithOffset);
        return jsonMapper.toJsonDtos(jsonBEntities.getContent());
    }

    public List<JsonDto> findByComplexRequest(MultipleOperationShell searchParamShell) {
        PageRequestWithOffset pageRequestWithOffset = operationService.buildPageSettings(searchParamShell.getPageAttribute(), SORTED_FIELDS);
        Specification<JsonBEntity> specification = operationService.buildComplexSpecificationByParams(searchParamShell.getSearch(), searchParamShell.getExternalGlue());
        Page<JsonBEntity> jsonBEntities = jsonBRepository.findAll(specification, pageRequestWithOffset);
        return jsonMapper.toJsonDtos(jsonBEntities.getContent());
    }
}
