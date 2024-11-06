package company.thehubs.transformation.infrastructure.adapter.input;

import company.thehubs.transformation.domain.model.Source;
import company.thehubs.transformation.domain.model.Target;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransformationController {

    private final MessageChannel inputChannel;
    private final PollableChannel outputChannel;

    public TransformationController(@Qualifier("inputChannel") MessageChannel inputChannel, @Qualifier("outputChannel") PollableChannel outputChannel) {
        this.inputChannel = inputChannel;
        this.outputChannel = outputChannel;
    }

    @PostMapping(value = "/transform", consumes = "application/xml", produces = "application/json")
    public ResponseEntity<Target> transform(@RequestBody Source source) {
        // Enviar el mensaje al inputChannel y recibir el resultado en outputChannel
        inputChannel.send(MessageBuilder.withPayload(source).build());

        // Recibir la respuesta desde el canal de salida (outputChannel)
        Target result = (Target) outputChannel.receive().getPayload();
        return ResponseEntity.ok(result);
    }
}