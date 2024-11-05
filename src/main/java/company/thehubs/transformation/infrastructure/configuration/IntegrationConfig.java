package company.thehubs.transformation.infrastructure.configuration;

import company.thehubs.transformation.infrastructure.integration.transformer.SourceToTargetTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow xmlToJsonFlow(SourceToTargetTransformer transformer) {
        return IntegrationFlows.from(inputChannel())
                .transform(transformer::transform)
                .get();
    }
}