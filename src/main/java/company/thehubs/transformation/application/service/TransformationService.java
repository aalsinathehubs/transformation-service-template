package company.thehubs.transformation.application.service;

import company.thehubs.transformation.application.port.TransformationUseCase;
import company.thehubs.transformation.domain.model.Source;
import company.thehubs.transformation.domain.model.Target;
import org.springframework.stereotype.Service;

@Service
public class TransformationService implements TransformationUseCase {

    @Override
    public Target transform(Source source) {
        Target target = new Target();
        target.setFullName(source.getName());
        target.setCategory(source.getAge() > 18 ? "Adult" : "Minor");
        return target;
    }
}