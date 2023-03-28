package info.jab.ms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${comment-service.base-url:http://localhost:8002}")
    private String commentServiceBaseUrl;

    @Bean
    WebClient webClient( WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl(commentServiceBaseUrl).build();
    }
}
