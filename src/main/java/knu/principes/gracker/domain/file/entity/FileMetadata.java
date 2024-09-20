package knu.principes.gracker.domain.file.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class FileMetadata {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    private String originalFileName;
    private String fileName; //uuid
    private String filePath;

    @Builder
    public FileMetadata(String originalFileName,String fileName, String filePath) {
        this.originalFileName = originalFileName;
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
