package company.thehubs.transformation.application.service;

import company.thehubs.transformation.domain.model.Source;
import company.thehubs.transformation.domain.model.Target;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransformationServiceTest {

    private TransformationService transformationService;

    @BeforeEach
    void setUp() {
        transformationService = new TransformationService();
    }

    @Test
    void shouldTransformSourceToTargetWithCorrectCategoryForAdult() {
        Source source = new Source();
        source.setName("John Doe");
        source.setAge(30);

        Target target = transformationService.transform(source);

        assertEquals("John Doe", target.getFullName());
        assertEquals("Adult", target.getCategory());
    }

    @Test
    void shouldTransformSourceToTargetWithCorrectCategoryForMinor() {
        Source source = new Source();
        source.setName("Jane Doe");
        source.setAge(15);

        Target target = transformationService.transform(source);

        assertEquals("Jane Doe", target.getFullName());
        assertEquals("Minor", target.getCategory());
    }

    @Test
    void shouldTransformSourceToTargetWithCorrectCategoryForSenior() {
        Source source = new Source();
        source.setName("Alice Smith");
        source.setAge(70);

        Target target = transformationService.transform(source);

        assertEquals("Alice Smith", target.getFullName());
        assertEquals("Senior", target.getCategory());
    }
}