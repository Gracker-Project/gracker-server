package knu.principes.gracker.domain.reqirement.entity;

import jakarta.persistence.*;
import knu.principes.gracker.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "requirement")
public class Requirement extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requirement_id")
    private Long id;

    @Column(name = "requirement_name")
    private String requirementName;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graduation_requirement_set_id", nullable = false)
    private GraduationRequirementSet graduationRequirementSet;


    @Builder
    public Requirement(String requirementName, String description, GraduationRequirementSet graduationRequirementSet) {
        this.requirementName = requirementName;
        this.description = description;
        this.graduationRequirementSet = graduationRequirementSet;
    }
}
