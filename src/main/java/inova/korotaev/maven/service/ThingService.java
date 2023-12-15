package inova.korotaev.maven.service;

import inova.korotaev.maven.dto.ThingDto;
import inova.korotaev.maven.mapper.ThingMapper;
import inova.korotaev.maven.model.Thing;
import inova.korotaev.maven.repository.ThingRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.sergkorot.dynamic.model.PageAttribute;
import ru.sergkorot.dynamic.model.paging.PageRequestWithOffset;
import ru.sergkorot.dynamic.model.shell.CommonOperationShell;
import ru.sergkorot.dynamic.model.shell.MultipleOperationShell;
import ru.sergkorot.dynamic.operation.SpecificationOperationService;
import ru.sergkorot.dynamic.util.QuerySpreadUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ThingService {

    private final ThingRepository thingRepository;
    private final ThingMapper thingMapper;
    private final SpecificationOperationService<Thing> operationService;

    private static final List<String> SORTED_FIELDS = List.of("name", "description");

    public ThingDto save(ThingDto thingDto) {
        Thing thing = thingMapper.toThing(thingDto);
        return thingMapper.toThingDto(thingRepository.save(thing));
    }

    public ThingDto getById(UUID id) {
        Thing thing = thingRepository.findById(id)
                .orElseThrow(EntityExistsException::new);
        return thingMapper.toThingDto(thing);
    }

    public List<ThingDto> findByBaseRequest(CommonOperationShell searchParamShell) {
        PageRequestWithOffset pageRequestWithOffset = operationService.buildPageSettings(searchParamShell.getPageAttribute(), SORTED_FIELDS);
        Specification<Thing> specification = operationService.buildBaseByParams(searchParamShell.getBaseSearchParams(), searchParamShell.getGlue());
        Page<Thing> things = thingRepository.findAll(specification, pageRequestWithOffset);
        return thingMapper.toThingDtoList(things.getContent());
    }

    public List<ThingDto> findByComplexRequest(MultipleOperationShell searchParamShell) {
        PageRequestWithOffset pageRequestWithOffset = operationService.buildPageSettings(searchParamShell.getPageAttribute(), SORTED_FIELDS);
        Specification<Thing> specification = operationService.buildComplexByParams(searchParamShell.getSearch(), searchParamShell.getExternalGlue());
        Page<Thing> things = thingRepository.findAll(specification, pageRequestWithOffset);
        return thingMapper.toThingDtoList(things.getContent());
    }

    public List<ThingDto> findByQuery(String query, Integer limit, Integer offset, String sortBy) {

        PageAttribute pageAttribute = new PageAttribute();
        pageAttribute.setLimit(limit);
        pageAttribute.setOffset(offset);
        pageAttribute.setSortBy(sortBy);
        PageRequestWithOffset pageRequestWithOffset = operationService.buildPageSettings(pageAttribute, SORTED_FIELDS);

        MultipleOperationShell multipleOperationShell = QuerySpreadUtils.transformToOperationShell(query);
        Specification<Thing> specification = operationService.buildComplexByParams(multipleOperationShell.getSearch(), multipleOperationShell.getExternalGlue());

        Page<Thing> things = thingRepository.findAll(specification, pageRequestWithOffset);
        return thingMapper.toThingDtoList(things.getContent());
    }
}
