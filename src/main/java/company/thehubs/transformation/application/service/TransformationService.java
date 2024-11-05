package company.thehubs.transformation.application.service;

import company.thehubs.transformation.application.port.TransformationUseCase;
import company.thehubs.transformation.domain.model.Source;
import company.thehubs.transformation.domain.model.Target;
import org.springframework.stereotype.Service;

@Service
public class TransformationService implements TransformationUseCase {

    @Override
    public Target transform(Source source) {
        int age = source.getAge();
        Target target = new Target();
        
        target.setFullName(source.getName());
        if (age >= 18 && age <= 25) {
            target.setCategory("Young Adult");
        } else if (age > 25 && age <= 65) {
            target.setCategory("Adult");
        } else if (age > 65) {
            target.setCategory("Senior");
        } else {
            target.setCategory("Minor");
        }
        return target;
    }
}