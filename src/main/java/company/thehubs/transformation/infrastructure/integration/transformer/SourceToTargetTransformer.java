package company.thehubs.transformation.infrastructure.integration.transformer;

import company.thehubs.transformation.application.port.TransformationUseCase;
import company.thehubs.transformation.domain.model.Source;
import company.thehubs.transformation.domain.model.Target;
import org.springframework.stereotype.Component;

@Component
public class SourceToTargetTransformer {

    private final TransformationUseCase transformationService;

    public SourceToTargetTransformer(TransformationUseCase transformationService) {
        this.transformationService = transformationService;
    }

    public Target transform(Source source) {
        return transformationService.transform(source);
    }
}