package knu.principes.gracker.domain.file.service;

import knu.principes.gracker.domain.file.entity.FileMetadata;
import knu.principes.gracker.domain.file.repository.FileMetadataRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileService {
    private final FileMetadataRepository fileMetadataRepository;

    @Transactional
    public Long saveFile(MultipartFile file, String uploadDir) throws IOException {
        String fileName = UUID.randomUUID().toString();
        Path destinationPath = Paths.get(uploadDir).resolve(fileName);

        Files.copy(file.getInputStream(), destinationPath);

        FileMetadata fileMetadata = FileMetadata.builder()
                .originalFileName(file.getOriginalFilename())
                .fileName(fileName)
                .filePath(destinationPath.toString())
                .build();

        return fileMetadataRepository.save(fileMetadata).getId();
    }

}
