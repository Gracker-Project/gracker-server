package knu.principes.gracker.domain.reqirement.repository;

import knu.principes.gracker.domain.reqirement.entity.GraduationRequirementSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GraduationRequirementSetRepository extends JpaRepository<GraduationRequirementSet, Long> {

}
