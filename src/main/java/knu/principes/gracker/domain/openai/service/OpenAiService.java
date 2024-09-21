package knu.principes.gracker.domain.openai.service;

import knu.principes.gracker.domain.subject.entity.Subject;
import knu.principes.gracker.domain.subject.service.SubjectService;
import knu.principes.gracker.global.config.OpenAiConfig;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OpenAiService {
    private final OpenAiConfig openAiConfig;  // OpenAiConfig 에서 설정된 값들 주입
    private final RestTemplate restTemplate;  // RestTemplate 주입
    private final SubjectService subjectService;

    /**
     * OpenAI API에 학생이 수강한 과목을 바탕으로 코스 추천을 요청
     * @param completedSubjects 학생이 수강한 과목 리스트
     * @return 추천된 코스
     */
    public String recommendCourses(List<Subject> completedSubjects) {
        // OpenAI에 전송할 프롬프트 생성
        String prompt = createPrompt(completedSubjects);

        // 요청 본문 구성
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo");  // 모델 선택
        requestBody.put("messages", List.of(new JSONObject()
                .put("role", "user")
                .put("content", prompt)));
        requestBody.put("max_tokens", 150);
        requestBody.put("temperature", 0.7);

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openAiConfig.getApiKey());  // API 키 주입
        headers.set("Content-Type", "application/json");

        // 요청 엔티티 생성
        HttpEntity<String> entity = new HttpEntity<>(requestBody.toString(), headers);

        // OpenAI API 호출
        ResponseEntity<String> response = restTemplate.exchange(
                openAiConfig.getOpenAiUrl(),  // OpenAI API URL 사용
                HttpMethod.POST,
                entity,
                String.class
        );

        // 응답 처리
        return extractRecommendation(response.getBody());
    }

    /**
     * 학생의 수강한 과목을 기반으로 OpenAI API에 전달할 프롬프트 생성
     * @param completedSubjects 수강 완료 과목 리스트
     * @return 생성된 프롬프트
     */
    private String createPrompt(List<Subject> completedSubjects) {
        StringBuilder promptBuilder = new StringBuilder();

        // 수강한 과목들을 프롬프트에 추가
        promptBuilder.append("학생이 수강한 과목:\n");
        String completedCourses = completedSubjects.stream()
                .map(subject -> "- " + subject.getSubjectName())  // 과목 이름을 포함
                .collect(Collectors.joining("\n"));
        promptBuilder.append(completedCourses);

        // 추천할 과목 요청
        promptBuilder.append("\n이후에 추천할 수 있는 과목 코스를 제시해줘.\n");
        promptBuilder.append("응답 형식은 다음과 같아야 한다:\n");
        promptBuilder.append("- 과목 코드: 과목명\n");
        promptBuilder.append("예시:\nCS201: 컴퓨터 과학 201\nMath202: 고급 수학");

        return promptBuilder.toString();
    }

    /**
     * OpenAI 응답에서 추천된 코스를 추출하는 메서드
     * @param responseBody OpenAI API 응답 본문
     * @return 추천된 코스
     */
    private String extractRecommendation(String responseBody) {
            JSONObject responseJson = new JSONObject(Integer.parseInt(responseBody));
        return responseJson.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content")
                .trim();
    }

}
