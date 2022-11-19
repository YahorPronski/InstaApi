package com.company.postservice.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class FileUploadUtil {

    @Value("${file.upload.root.path}")
    private String uploadRootPath;

    public void saveFile(String uploadDir, String fileName, MultipartFile file) {
        Path uploadPath = Paths.get(uploadRootPath + uploadDir);

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                log.error("Could not create directory {}", uploadPath.toAbsolutePath(), e);
            }
        }

        Path filePath = uploadPath.resolve(fileName);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath);
        } catch (IOException e) {
            log.error("Could not save file {}", filePath.toAbsolutePath(), e);
        }
    }
}
