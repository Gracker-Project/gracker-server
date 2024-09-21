package knu.principes.gracker.domain.openai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import knu.principes.gracker.domain.subject.dto.ResponseSubjectDto;
import knu.principes.gracker.global.config.OpenAiConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class OpenAiService {
    private final OpenAiConfig openAiConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    /**
     * OpenAI API에 학생이 수강한 과목을 바탕으로 코스 추천을 요청
     * @param completedSubjects 학생이 수강한 과목 리스트
     * @return 추천된 코스
     */
    public String recommendCourses(List<ResponseSubjectDto> completedSubjects) {
        // OpenAI에 전송할 프롬프트 생성
        String prompt = createPrompt(completedSubjects);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");

        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);
        requestBody.put("messages", Collections.singletonList(message));

        requestBody.put("max_tokens", 150);
        requestBody.put("temperature", 0.7);

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiConfig.getApiKey());

        // 요청 엔티티 생성
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // OpenAI API 호출
            ResponseEntity<String> response = restTemplate.exchange(
                    openAiConfig.getOpenAiUrl(),
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            // 응답 처리
            return extractRecommendation(response.getBody());

        } catch (Exception e) {
            log.error("Error while calling OpenAI API: ", e);
            throw new RuntimeException("Failed to get course recommendations from OpenAI.", e);
        }
    }

    /**
     * 학생의 수강한 과목을 기반으로 OpenAI API에 전달할 프롬프트 생성
     * @param completedSubjects 수강 완료 과목 리스트
     * @return 생성된 프롬프트
     */
    private String createPrompt(List<ResponseSubjectDto> completedSubjects) {
        StringBuilder promptBuilder = new StringBuilder();

        // 수강한 과목들을 프롬프트에 추가
        promptBuilder.append("학생이 수강한 과목:\n");
        String completedCourses = completedSubjects.stream()
                .map(subject -> "- " + subject.subjectName())
                .collect(Collectors.joining("\n"));
        promptBuilder.append(completedCourses);

        // 추천할 과목 요청
        promptBuilder.append("\n이후에 추천할 수 있는 과목 코스를 제시해줘.\n");
        promptBuilder.append("응답 형식은 다음과 같아야 한다:\n");
        // 예시 형식. 이후에 수정하기
        promptBuilder.append("예시:\nCS201: 컴퓨터 과학 201\nMath202: 고급 수학");

        return promptBuilder.toString();
    }

    /**
     * OpenAI 응답에서 추천된 코스를 추출하는 메서드
     * @param responseBody OpenAI API 응답 본문
     * @return 추천된 코스
     */
    private String extractRecommendation(String responseBody) {
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode choices = root.path("choices");
            if (!choices.isArray() || choices.size() == 0) {
                throw new RuntimeException("No choices found in OpenAI response.");
            }

            JsonNode firstChoice = choices.get(0);
            JsonNode message = firstChoice.path("message");
            if (message.isMissingNode()) {
                throw new RuntimeException("No message found in first choice.");
            }

            String content = message.path("content").asText();
            if (content == null || content.isEmpty()) {
                throw new RuntimeException("Empty content in message.");
            }

            return content.trim();
        } catch (Exception e) {
            log.error("Error parsing OpenAI response: ", e);
            throw new RuntimeException("Failed to parse OpenAI response.");
        }
    }

}
