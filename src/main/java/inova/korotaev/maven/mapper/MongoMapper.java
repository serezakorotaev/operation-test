package inova.korotaev.maven.mapper;

import inova.korotaev.maven.dto.MongoDto;
import inova.korotaev.maven.model.mongo.MongoEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MongoMapper {

    MongoEntity toMongoEntity(MongoDto mongoDto);

    MongoDto toMongoDto(MongoEntity mongoEntity);

    List<MongoDto> toMongoDtos(List<MongoEntity> mongoEntities);

}
