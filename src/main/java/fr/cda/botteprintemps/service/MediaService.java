package fr.cda.botteprintemps.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MediaService {

    private final Path root = Paths.get("medias");

    private void init() {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for Media !");
        }
    }

    public ResponseEntity<String> save(MultipartFile file) {
        if (file.isEmpty() || file.getOriginalFilename() == null) {
            return new ResponseEntity<>("Aucun fichier soumis", HttpStatus.BAD_REQUEST);
        }
        try {
            init();
            String uniqId = UUID.randomUUID().toString();
            String fileName = uniqId + "-" + StringUtils.cleanPath(file.getOriginalFilename());
            Path targetLocation = root.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>("Téléversement de " + fileName + " réussit", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Erreur lors du téléversement du fichier", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
