package knu.principes.gracker.domain.subject.repository;

import knu.principes.gracker.domain.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
