package ksutimetable.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriTemplateHandler;

import java.util.Collections;

@Configuration
public class RestTemplateConfig {

    @Value("${timetable.url}")
    private String defaultUri;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(getTemplateHandler());
        restTemplate.getMessageConverters().add(getJackson2Converter());
        return restTemplate;
    }

    private MappingJackson2HttpMessageConverter getJackson2Converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        return converter;
    }

    private UriTemplateHandler getTemplateHandler() {
        return new DefaultUriBuilderFactory(defaultUri);
    }
}
