package knu.principes.gracker.domain.subject.repository;

import knu.principes.gracker.domain.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<List<Subject>> findAllByStudentId(Long studentId);

    Optional<Subject> findBySubjectName(String subjectName);
}
