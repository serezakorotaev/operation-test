package inova.korotaev.maven.mapper;

import inova.korotaev.maven.dto.JsonDto;
import inova.korotaev.maven.model.JsonBEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JsonMapper {

    @Mapping(target = "value", expression = "java(entity.getValue().toString())")
    JsonDto toJsonDto(JsonBEntity entity);

    List<JsonDto> toJsonDtos(List<JsonBEntity> entity);

    JsonBEntity toJsonBEntity(JsonDto jsonDto);
}
