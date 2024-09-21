package knu.principes.gracker.domain.subject.dto;

import knu.principes.gracker.domain.subject.entity.Subject;

public record ResponseSubjectDto(
        Long id,
        String subjectName,
        Integer credit,
        Double grade,
        String subjectType
) {
    public static ResponseSubjectDto fromEntity(Subject subject) {
        return new ResponseSubjectDto(
                subject.getId(),
                subject.getSubjectName(),
                subject.getCredit(),
                subject.getGrade(),
                subject.getSubjectType().name()  // Enum을 문자열로 변환
        );
    }
}
