package knu.principes.gracker.domain.reqirement.entity;

import jakarta.persistence.*;
import knu.principes.gracker.domain.base.BaseTimeEntity;
import knu.principes.gracker.domain.reqirement.entity.track.Track;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "graduation_requirement_set", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"track_id", "admission_year"})
})
public class GraduationRequirementSet extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graduation_requirement_set_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    @Column(name = "admission_year", nullable = false)
    private Integer admissionYear; // 입학년도

    @Column(name = "csv_file_path", nullable = false)
    private String csvFilePath; // csv 파일 경로

    @OneToMany(mappedBy = "graduationRequirementSet",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Requirement> requirements = new ArrayList<>();

    @Builder
    public GraduationRequirementSet(Track track, Integer admissionYear, String csvFilePath) {
        this.track = track;
        this.admissionYear = admissionYear;
        this.csvFilePath = csvFilePath;
    }
}
