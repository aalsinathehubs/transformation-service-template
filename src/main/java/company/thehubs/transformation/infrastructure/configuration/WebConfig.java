package company.thehubs.transformation.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;

import java.util.List;

@Configuration
public class WebConfig {

    @Bean
    public Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter() {
        return new Jaxb2RootElementHttpMessageConverter();
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jaxb2RootElementHttpMessageConverter());
    }
}