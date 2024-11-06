package company.thehubs.transformation.infrastructure.adapater.input;

import company.thehubs.transformation.application.service.TransformationService;
import company.thehubs.transformation.domain.model.Source;
import company.thehubs.transformation.domain.model.Target;
import company.thehubs.transformation.infrastructure.adapter.input.TransformationController;
import company.thehubs.transformation.infrastructure.configuration.IntegrationConfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TransformationController.class)
@Import(IntegrationConfig.class)
public class TransformationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("inputChannel")
    private MessageChannel inputChannel;

    @MockBean
    @Qualifier("outputChannel")
    private PollableChannel outputChannel;

    @MockBean
    private TransformationService transformationService;

    @Test
    public void shouldTransformSourceToTargetSuccessfully() throws Exception {
        // Configuración de objetos de prueba
        Source source = new Source();
        source.setName("John Doe");
        source.setAge(30);

        Target target = new Target();
        target.setFullName("John Doe");
        target.setCategory("Adult");

        // Crear el mensaje de respuesta sin especificar el tipo de carga
        Message<?> responseMessage = MessageBuilder.withPayload(target).build();
        
        // Utilizar doReturn en lugar de when para evitar problemas de tipo
        doReturn(responseMessage).when(outputChannel).receive();

        // Crear XML de entrada
        String sourceXml = "<Source><name>John Doe</name><age>30</age></Source>";

        // Enviar la solicitud al controlador
        mockMvc.perform(post("/api/transform")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_JSON)
                .content(sourceXml))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("John Doe"))
                .andExpect(jsonPath("$.category").value("Adult"));
    }
}