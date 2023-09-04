package ksutimetable.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.util.MimeType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Value("${timetable.url}")
    private String timetableUrl;

    @Bean
    WebClient getWebClient() {

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        return WebClient.builder()
                .baseUrl(timetableUrl)
                .codecs(clientCodecConfigurer -> clientCodecConfigurer.customCodecs()
                        .register(new Jackson2JsonDecoder(
                                new ObjectMapper(),
                                new MimeType("text", "html")
                        )))
                .defaultUriVariables(Collections.singletonMap("url", timetableUrl))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

    }

}
