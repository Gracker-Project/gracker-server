package knu.principes.gracker.domain.reqirement.repository;

import knu.principes.gracker.domain.reqirement.entity.track.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {

}
