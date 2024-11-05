package company.thehubs.transformation.infrastructure.adapter.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonTransformer {

    private final ObjectMapper objectMapper;

    public JsonTransformer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Convierte un objeto Java en una cadena JSON.
     *
     * @param object el objeto a transformar
     * @return representación JSON del objeto
     * @throws JsonProcessingException si ocurre un error durante la conversión
     */
    public String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}