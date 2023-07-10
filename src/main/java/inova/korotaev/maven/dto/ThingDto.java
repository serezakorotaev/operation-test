package inova.korotaev.maven.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class ThingDto {

    private UUID id;

    private String name;

    private String description;

    private boolean flag;

    private Integer version;

    private Instant createdAt;

    private Instant updatedAt;
}
