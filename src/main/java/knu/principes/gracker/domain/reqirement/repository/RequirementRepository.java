package knu.principes.gracker.domain.reqirement.repository;

import knu.principes.gracker.domain.reqirement.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementRepository extends JpaRepository<Requirement, Long> {
}
