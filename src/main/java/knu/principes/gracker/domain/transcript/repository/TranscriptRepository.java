package knu.principes.gracker.domain.transcript.repository;

import knu.principes.gracker.domain.transcript.entity.Transcript;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscriptRepository extends JpaRepository<Transcript, Long> {
}
