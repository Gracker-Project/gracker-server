package knu.principes.gracker.domain.subject.dto;

import knu.principes.gracker.domain.student.entity.Student;
import knu.principes.gracker.domain.subject.entity.Subject;
import knu.principes.gracker.domain.subject.entity.SubjectType;

public record RequestSubjectDto(
        String subjectName,
        Integer credit,
        Double grade,
        String subjectType
) {
    public static Subject toEntity(RequestSubjectDto requestSubjectDto, Student student) {
        return Subject.builder()
                .subjectName(requestSubjectDto.subjectName())
                .credit(requestSubjectDto.credit())
                .grade(requestSubjectDto.grade())
                .student(student)
                .subjectType(SubjectType.valueOf(requestSubjectDto.subjectType())) // 문자열을 Enum으로 변환
                .build();
    }
}
