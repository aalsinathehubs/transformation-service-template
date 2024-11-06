package company.thehubs.transformation.infrastructure.adapater.input;

import company.thehubs.transformation.TransformationServiceTemplateApplication;
import company.thehubs.transformation.domain.model.Source;
import company.thehubs.transformation.infrastructure.configuration.IntegrationConfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = { TransformationServiceTemplateApplication.class, IntegrationConfig.class })
@AutoConfigureMockMvc
public class TransformationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private XmlMapper xmlMapper;

    private Source source;

    @BeforeEach
    void setUp() {
        xmlMapper = new XmlMapper();
        source = new Source();
        source.setName("John Doe");
        source.setAge(30);
    }

    @Test
    public void shouldTransformSourceToTargetSuccessfully() throws Exception {
        String sourceXml = xmlMapper.writeValueAsString(source);

        mockMvc.perform(post("/api/transform")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_JSON)
                .content(sourceXml))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("John Doe"))
                .andExpect(jsonPath("$.category").value("Adult"));
    }
    
    @Test
    void shouldReturnUnsupportedMediaTypeForInvalidContentType() throws Exception {
        mockMvc.perform(post("/api/transform")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(source)))
                .andExpect(status().isUnsupportedMediaType());
    }
}