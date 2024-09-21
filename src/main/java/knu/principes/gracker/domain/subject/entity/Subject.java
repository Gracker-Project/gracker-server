package knu.principes.gracker.domain.subject.entity;

import jakarta.persistence.*;
import knu.principes.gracker.domain.student.entity.Student;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "subject")
public class Subject {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    @Column(name = "subject_number")
    private String subjectName;

    private Integer credit; // 학점

    @Column(name = "grade")
    private Double grade; // 성적(평점)

    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Builder
    public Subject(String subjectName, Integer credit, Double grade, SubjectType subjectType, Student student) {
        this.subjectName = subjectName;
        this.credit = credit;
        this.grade = grade;
        this.subjectType = subjectType;
        this.student = student;
    }
}
