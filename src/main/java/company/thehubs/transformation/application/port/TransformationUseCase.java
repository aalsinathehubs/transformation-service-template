package company.thehubs.transformation.application.port;

import company.thehubs.transformation.domain.model.Source;
import company.thehubs.transformation.domain.model.Target;

public interface TransformationUseCase {
    Target transform(Source source);
}