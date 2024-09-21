package knu.principes.gracker.domain.student.service;

import knu.principes.gracker.domain.student.entity.Student;
import knu.principes.gracker.domain.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;

    public boolean isExistEmail(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자가 없습니다."));
    }
}
