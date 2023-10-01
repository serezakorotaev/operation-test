package inova.korotaev.maven.model.mongo;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@FieldNameConstants
public class MongoEntity {

    @Id
    private String id;
    private String content;
    private Integer porch;
    private Boolean flag;
    private SomeObject someObject;

    @Data
    public static class SomeObject {
        private String content;
        private Integer porch;
        private Boolean flag;
    }
}
