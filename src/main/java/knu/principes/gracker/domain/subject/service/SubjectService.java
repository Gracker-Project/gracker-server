package knu.principes.gracker.domain.subject.service;

import knu.principes.gracker.domain.student.entity.Student;
import knu.principes.gracker.domain.student.repository.StudentRepository;
import knu.principes.gracker.domain.subject.dto.RequestSubjectDto;
import knu.principes.gracker.domain.subject.dto.ResponseSubjectDto;
import knu.principes.gracker.domain.subject.entity.Subject;
import knu.principes.gracker.domain.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static knu.principes.gracker.domain.subject.dto.ResponseSubjectDto.fromEntity;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public ResponseSubjectDto addSubject(RequestSubjectDto requestSubjectDto, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));
        Subject subject = RequestSubjectDto.toEntity(requestSubjectDto,student);

        subjectRepository.save(subject);
        return ResponseSubjectDto.fromEntity(subject);
    }

    @Transactional
    public List<ResponseSubjectDto> addAllSubjects(List<RequestSubjectDto> requestSubjectDtos, Long studentId) {
        if (requestSubjectDtos == null || requestSubjectDtos.isEmpty()) {
            throw new IllegalArgumentException("Subject list must not be empty");
        }
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("해당 학생이 존재하지 않습니다."));

        log.info("Adding {} subjects", requestSubjectDtos.size());

        List<Subject> subjects = requestSubjectDtos.stream()
                .map(requestSubjectDto -> RequestSubjectDto.toEntity(requestSubjectDto, student))
                .collect(Collectors.toList());
        subjectRepository.saveAll(subjects);

        List<ResponseSubjectDto> responseDtos = subjects.stream()
                .map(ResponseSubjectDto::fromEntity)
                .collect(Collectors.toList());

        log.info("Successfully added {} subjects", responseDtos.size());

        return responseDtos;
    }

    public ResponseSubjectDto getSubject(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new IllegalArgumentException("해당 과목이 존재하지 않습니다."));

        return fromEntity(subject);
    }

    public List<ResponseSubjectDto> getAllSubjects(Long studentId) {
        List<Subject> subjects = subjectRepository.findAllByStudentId(studentId).orElseThrow(() -> new IllegalArgumentException("해당 학생의 수강한 과목이 존재하지 않습니다."));

        return subjects.stream().map(ResponseSubjectDto::fromEntity).collect(Collectors.toList());
    }
}
