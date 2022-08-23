package ksutimetable.services;

import ksutimetable.configs.SpringAppConfig;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class RequestService {

    WebClient client;

    public String doPost(MultiValueMap<String, String> requestParams){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringAppConfig.class);
        var client = context.getBean(WebClient.class);
        return client
                .post()
                .uri("timetable.php")
                .bodyValue(requestParams)
                .retrieve()
                .bodyToMono(String.class).block();
    }

}
