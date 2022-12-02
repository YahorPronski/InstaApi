package com.company.postservice.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class FileUtil {

    @Value("${post.upload.root.path}")
    private String uploadRootPath;

    public void saveFile(String uploadDir, String fileName, byte[] file) {
        Path uploadPath = Paths.get(uploadRootPath + uploadDir);

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                log.error("Could not create directory {}", uploadPath.toAbsolutePath(), e);
            }
        }

        Path filePath = uploadPath.resolve(fileName);
        try (InputStream inputStream = new ByteArrayInputStream(file)) {
            Files.copy(inputStream, filePath);
        } catch (IOException e) {
            log.error("Could not save file {}", filePath.toAbsolutePath(), e);
        }
    }

    public byte[] convertFromBase64(String fileBase64) {
        return Base64.decodeBase64(fileBase64);
    }
}
