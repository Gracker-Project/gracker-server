package knu.principes.gracker.domain.student.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import knu.principes.gracker.domain.subject.entity.Subject;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "student")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Email
    @Column(
            name = "email",
            nullable = false,
            length = 50,
            unique = true
    )
    private String email;

    @Column(
            name = "student_number",
            nullable = false,
            length = 15,
            unique = true
    )
    private String studentNumber;

    @Column(
            name = "name",
            length = 50
    )
    private String studentName;

    @Enumerated(EnumType.STRING)
    @Column(name = "major_type")
    private MajorType majorType;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Subject> subjects = new ArrayList<>();

    @Builder
    public Student(String email,String studentNumber, MajorType majorType,String studentName, Role role, List<Subject> subjects) {
        this.email = email;
        this.studentNumber = studentNumber;
        this.majorType = majorType;
        this.studentName = studentName;
        this.role = role;
        this.subjects = subjects;
    }
}

