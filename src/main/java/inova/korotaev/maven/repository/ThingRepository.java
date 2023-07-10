package inova.korotaev.maven.repository;

import inova.korotaev.maven.model.Thing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ThingRepository extends JpaRepository<Thing, UUID>, JpaSpecificationExecutor<Thing> {
}
