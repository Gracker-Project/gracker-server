package knu.principes.gracker.domain.reqirement.service;

import knu.principes.gracker.domain.reqirement.repository.GraduationRequirementSetRepository;
import knu.principes.gracker.domain.reqirement.repository.RequirementRepository;
import knu.principes.gracker.domain.reqirement.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RequirementService {
    private final GraduationRequirementSetRepository graduationRequirementSetRepository;
    private final TrackRepository trackRepository;
    private final RequirementRepository requirementRepository;

    @Transactional

}
