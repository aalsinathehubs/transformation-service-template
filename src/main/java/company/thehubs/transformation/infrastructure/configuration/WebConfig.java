package company.thehubs.transformation.infrastructure.configuration;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import java.util.List;

@Configuration
public class WebConfig {

    @Bean
    public MappingJackson2XmlHttpMessageConverter xmlHttpMessageConverter() {
        return new MappingJackson2XmlHttpMessageConverter(new XmlMapper());
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(xmlHttpMessageConverter());
    }
}