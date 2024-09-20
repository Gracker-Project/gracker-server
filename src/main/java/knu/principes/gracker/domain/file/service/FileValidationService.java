package knu.principes.gracker.domain.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileValidationService {
    boolean validateFileExtension(MultipartFile file);

}
