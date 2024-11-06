package company.thehubs.transformation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration"
})
class TransformationServiceTemplateApplicationTests {

	@Test
	void contextLoads() {
		// Este test verifica que el contexto de la aplicacion se cargue correctamente
	}

}
