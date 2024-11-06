package company.thehubs.transformation.infrastructure.adapater.input;

import company.thehubs.transformation.domain.model.Source;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransformationControllerTest {

    @MockBean
    private MessageChannel inputChannel;

    @MockBean
    private PollableChannel outputChannel;

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
    void shouldTransformSourceToTargetSuccessfully() throws Exception {
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