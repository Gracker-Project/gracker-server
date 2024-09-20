package knu.principes.gracker.domain.file.repository;

import knu.principes.gracker.domain.file.entity.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileMetadataRepository extends JpaRepository<FileMetadata,Long> {
    List<FileMetadata> findByFileName(String fileName);

}
