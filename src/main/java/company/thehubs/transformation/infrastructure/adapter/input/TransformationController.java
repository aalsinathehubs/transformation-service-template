package company.thehubs.transformation.infrastructure.adapter.input;

import company.thehubs.transformation.application.port.TransformationUseCase;
import company.thehubs.transformation.domain.model.Source;
import company.thehubs.transformation.domain.model.Target;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransformationController {

    private final TransformationUseCase transformationService;

    public TransformationController(TransformationUseCase transformationService) {
        this.transformationService = transformationService;
    }

    @PostMapping(value = "/transform", consumes = "application/xml", produces = "application/json")
    public Target transform(@RequestBody Source source) {
        return transformationService.transform(source);
    }
}