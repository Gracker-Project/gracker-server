package knu.principes.gracker.domain.transcript.entity;

import jakarta.persistence.*;
import knu.principes.gracker.domain.base.BaseTimeEntity;
import knu.principes.gracker.domain.student.entity.Student;
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
@Table(name = "transcript")
public class Transcript extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transcript_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transcript_status")
    private TranscriptStatus transcriptStatus;

    @Column(name = "gpa")
    private Double gpa;

    @Column(name = "total_credit")
    private Integer totalCredit;

    @OneToOne(fetch = FetchType.LAZY)
    private Student student;

    @OneToMany(mappedBy = "transcript",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Subject> subjects = new ArrayList<>();

    @Builder
    public Transcript(TranscriptStatus transcriptStatus, Double gpa, Integer totalCredit, Student student) {
        this.transcriptStatus = transcriptStatus;
        this.gpa = gpa;
        this.totalCredit = totalCredit;
        this.student = student;
    }
}
