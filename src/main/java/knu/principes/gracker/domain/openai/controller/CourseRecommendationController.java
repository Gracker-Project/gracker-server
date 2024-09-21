package knu.principes.gracker.domain.openai.controller;

import knu.principes.gracker.domain.openai.service.OpenAiService;
import knu.principes.gracker.domain.subject.entity.Subject;
import knu.principes.gracker.domain.subject.service.SubjectService;
import knu.principes.gracker.global.security.details.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class CourseRecommendationController {
    private final OpenAiService openAiService;
    private final SubjectService subjectService;


    @PostMapping("/courses")
    @PreAuthorize("hasRole('STUDENT') and isAuthenticated()")
    public ResponseEntity<?> recommendCourses(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            ) {
        Long studentId = principalDetails.getStudent().getId();




    }
}
