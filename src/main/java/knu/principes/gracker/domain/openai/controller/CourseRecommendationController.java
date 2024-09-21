package knu.principes.gracker.domain.openai.controller;

import knu.principes.gracker.domain.openai.service.OpenAiService;
import knu.principes.gracker.domain.student.service.StudentService;
import knu.principes.gracker.domain.subject.dto.ResponseSubjectDto;
import knu.principes.gracker.domain.subject.entity.Subject;
import knu.principes.gracker.domain.subject.service.SubjectService;
import knu.principes.gracker.global.security.details.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class CourseRecommendationController {
    private final OpenAiService openAiService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    /**
     * 특정 학생의 수강 과목을 기반으로 코스 추천을 요청하는 엔드포인트
     *
     * @param studentId           추천을 요청할 학생의 ID
     * @param principalDetails    인증된 사용자 정보
     * @return 추천된 코스 목록
     */
    @PostMapping("/courses/{studentId}")
    @PreAuthorize("hasRole('STUDENT') or isAuthenticated()")
    public ResponseEntity<String> recommendCourses(
            @PathVariable Long studentId,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        // 인증된 사용자의 학생 ID 가져오기
        Long authenticatedStudentId = principalDetails.getStudent().getId();

        // PathVariable의 studentId와 인증된 사용자의 ID 비교
        if (!studentId.equals(authenticatedStudentId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("접근 권한이 없습니다.");
        }

        // 학생의 수강 과목 조회
        List<ResponseSubjectDto> completedSubjects = subjectService.getAllSubjects(studentId);

        // OpenAI를 통해 코스 추천 요청
        String recommendation = openAiService.recommendCourses(completedSubjects);

        return ResponseEntity.status(HttpStatus.OK).body(recommendation);
    }
}
