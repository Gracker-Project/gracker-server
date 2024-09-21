package knu.principes.gracker.domain.reqirement.entity;

import jakarta.persistence.*;
import knu.principes.gracker.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 등록학기가 8학기 이상인 재학생(단, 조기졸업은 6학기 이상부터 가능)
 * 소속 학과(부) 및 학번별로 해당 이수학점(졸업/전공/교양/복수전공/연계전공 등) 취득자
 * 전공, 교양 및 교직 등 해당 필수과목을 이수한 자
 * 성적 : 평점평균 1.7 이상(4.3만점, 2014학년도 1학기부터 성적은 F학점 포함) 단, 사범대학 2009학년도 이후 입학생은 1.9 이상, 약학과는 2.0 이상, 경상대학경영학부 2013학년도 이후 입학생은 2.0 이상)
 * 졸업자격 인정 요건을 충족한 자
 * 조기졸업 신청자
 **/

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "requirement")
public class Requirement extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requirement_id")
    private Long id;

    @Column(name = "content")
    private String content;

}
