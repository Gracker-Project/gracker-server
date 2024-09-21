package knu.principes.gracker.domain.subject.controller;

import jakarta.validation.Valid;
import knu.principes.gracker.domain.subject.dto.RequestSubjectDto;
import knu.principes.gracker.domain.subject.dto.ResponseSubjectDto;
import knu.principes.gracker.domain.subject.service.SubjectService;
import knu.principes.gracker.global.security.details.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('STUDENT') and isAuthenticated()")
    public ResponseEntity<ResponseSubjectDto> addSubject(
            @Valid @RequestBody RequestSubjectDto requestSubjectDto,
            @AuthenticationPrincipal PrincipalDetails principalDetails
            ) {

        Long studentId = principalDetails.getStudent().getId();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subjectService.addSubject(requestSubjectDto,studentId));
    }

    @PostMapping("/all")
    @PreAuthorize("hasRole('STUDENT') and isAuthenticated()")
    public ResponseEntity<List<ResponseSubjectDto>> addAllSubjects(
            @Valid @RequestBody List<@Valid RequestSubjectDto> requestSubjectDtos,
            @AuthenticationPrincipal PrincipalDetails principalDetails
            ) {

        Long studentId = principalDetails.getStudent().getId();
        List<ResponseSubjectDto> responseSubjectDtos = subjectService.addAllSubjects(requestSubjectDtos, studentId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseSubjectDtos);
    }

}
