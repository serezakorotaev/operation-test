package inova.korotaev.maven.mapper;

import inova.korotaev.maven.dto.ThingDto;
import inova.korotaev.maven.model.Thing;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ThingMapper {

    Thing toThing(ThingDto thingDto);

    ThingDto toThingDto(Thing thing);

    List<ThingDto> toThingDtoList(List<Thing> thingList);
}
