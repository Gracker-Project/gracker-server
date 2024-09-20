package knu.principes.gracker.domain.student.repository;

import knu.principes.gracker.domain.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
