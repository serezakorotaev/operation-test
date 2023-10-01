package inova.korotaev.maven.dto;

import inova.korotaev.maven.model.mongo.MongoEntity;
import lombok.Data;

@Data
public class MongoDto {

    private String id;
    private String content;
    private Integer porch;
    private Boolean flag;
    private MongoEntity.SomeObject someObject;
}
