package knu.principes.gracker.domain.subject.entity;

import jakarta.persistence.*;
import knu.principes.gracker.domain.transcript.entity.Transcript;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Double grade; // 성적, 숫자로 할지 영어로 할지

    @Enumerated(EnumType.STRING)
    private  SubjectType subjectType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transcript_id")
    private Transcript transcript;


}
