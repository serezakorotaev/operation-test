package inova.korotaev.maven.service;

import inova.korotaev.maven.dto.MongoDto;
import inova.korotaev.maven.mapper.MongoMapper;
import inova.korotaev.maven.model.mongo.MongoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import ru.sergkorot.dynamic.model.shell.CommonOperationShell;
import ru.sergkorot.dynamic.model.shell.MultipleOperationShell;
import ru.sergkorot.dynamic.operation.query.CriteriaOperationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongoService {

    private final MongoTemplate mongoTemplate;
    private final MongoMapper mapper;
    private final CriteriaOperationService operationService;
    private final static List<String> SORTED_FIELDS = List.of(MongoEntity.Fields.porch, MongoEntity.Fields.content);
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
}
