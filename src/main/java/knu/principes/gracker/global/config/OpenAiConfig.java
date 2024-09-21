package knu.principes.gracker.global.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Getter
public class OpenAiConfig {

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.api-url:https://api.openai.com/v1/chat/completions}")
    private String openAiUrl;  // 기본 URL 설정 또는 외부 설정에서 로드

    // RestTemplate 빈 생성
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}