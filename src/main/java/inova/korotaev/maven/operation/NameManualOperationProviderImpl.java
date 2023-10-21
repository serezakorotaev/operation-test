package inova.korotaev.maven.operation;

import inova.korotaev.maven.model.Thing;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.sergkorot.dynamic.model.BaseSearchParam;
import ru.sergkorot.dynamic.operation.ManualOperationProvider;

@Service
public class NameManualOperationProviderImpl implements ManualOperationProvider<Specification<Thing>> {
    @Override
    public String fieldName() {
        return Thing.Fields.name;
    }

    @Override
    public Specification<Thing> buildOperation(BaseSearchParam searchParam) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(searchParam.getName()),
                "CUSTOM OPERATION " + searchParam.getValue().toString().toUpperCase());
    }
}
