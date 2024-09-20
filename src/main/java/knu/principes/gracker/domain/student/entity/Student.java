package knu.principes.gracker.domain.student.entity;

import jakarta.persistence.*;
import knu.principes.gracker.domain.student.entity.oauth.OAuthUserInfo;
import knu.principes.gracker.domain.transcript.entity.Transcript;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "student")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

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

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Transcript transcript;

    @Builder
    public Student(String studentNumber, String studentName, Role role, Transcript transcript) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.role = role;
        this.transcript = transcript;
    }
}

