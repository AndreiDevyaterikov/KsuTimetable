package ksutimetable.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@AllArgsConstructor
public class RequestService {

    private final WebClient client;

    public String postRequest(MultiValueMap<String, String> requestParams){

        return client
                .post()
                .uri("timetable.php")
                .bodyValue(requestParams)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
