package inova.korotaev.maven.service;

import inova.korotaev.maven.dto.MongoDto;
import inova.korotaev.maven.mapper.MongoMapper;
import inova.korotaev.maven.model.mongo.MongoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.sergkorot.dynamic.model.PageAttribute;
import ru.sergkorot.dynamic.model.shell.CommonOperationShell;
import ru.sergkorot.dynamic.model.shell.MultipleOperationShell;
import ru.sergkorot.dynamic.operation.CriteriaOperationService;
import ru.sergkorot.dynamic.util.QuerySpreadUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoService {

    private final MongoTemplate mongoTemplate;
    private final MongoMapper mapper;
    private final CriteriaOperationService operationService;
    private static final List<String> SORTED_FIELDS = List.of(MongoEntity.Fields.porch, MongoEntity.Fields.content);

    public MongoDto save(MongoDto dto) {
        MongoEntity mongoEntity = mapper.toMongoEntity(dto);
        return mapper.toMongoDto(mongoTemplate.save(mongoEntity));
    }

    public List<MongoDto> findBase(CommonOperationShell shell) {
        Criteria criteria = operationService.buildBaseByParams(shell.getBaseSearchParams(), shell.getGlue());
        Query query = operationService.buildPageSettings(new Query(criteria), shell.getPageAttribute(), SORTED_FIELDS);
        return mapper.toMongoDtos(mongoTemplate.find(query, MongoEntity.class));

    }

    public List<MongoDto> findComplex(MultipleOperationShell shell) {
        Criteria criteria = operationService.buildComplexByParams(shell.getSearch(), shell.getExternalGlue());
        Query query = operationService.buildPageSettings(new Query(criteria), shell.getPageAttribute(), SORTED_FIELDS);
        return mapper.toMongoDtos(mongoTemplate.find(query, MongoEntity.class));
    }

    public List<MongoDto> findByQuery(String query, Integer limit, Integer offset, String sortBy) {

        PageAttribute pageAttribute = new PageAttribute();
        pageAttribute.setLimit(limit);
        pageAttribute.setOffset(offset);
        pageAttribute.setSortBy(sortBy);

        MultipleOperationShell multipleOperationShell = QuerySpreadUtils.transformToOperationShell(query);
        Criteria criteria = operationService.buildComplexByParams(multipleOperationShell.getSearch(), multipleOperationShell.getExternalGlue());

        Query mongoQuery = operationService.buildPageSettings(new Query(criteria), pageAttribute, SORTED_FIELDS);

        return mapper.toMongoDtos(mongoTemplate.find(mongoQuery, MongoEntity.class));
    }
}
