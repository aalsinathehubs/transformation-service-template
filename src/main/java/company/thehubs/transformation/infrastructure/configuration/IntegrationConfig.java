package company.thehubs.transformation.infrastructure.configuration;

import company.thehubs.transformation.application.service.TransformationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@Configuration
public class IntegrationConfig {

    @Bean
    @Primary
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    @Primary
    public PollableChannel outputChannel() {
        return new QueueChannel();
    }

    @Bean
    public IntegrationFlow xmlToJsonFlow(TransformationService transformationService) {
        return flow -> flow.channel(inputChannel())
                .transform(transformationService::transform)
                .channel(outputChannel());
    }
}