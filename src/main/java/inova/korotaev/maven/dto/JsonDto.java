package inova.korotaev.maven.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class JsonDto {
    private UUID id;
    private Object value;
}
