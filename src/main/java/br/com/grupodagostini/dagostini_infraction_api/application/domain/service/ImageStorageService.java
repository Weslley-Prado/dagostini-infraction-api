package br.com.grupodagostini.dagostini_infraction_api.application.domain.service;

import br.com.grupodagostini.dagostini_infraction_api.application.port.in.ImageStorageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageStorageService implements ImageStorageUseCase {

    private static final String UPLOAD_DIR = "uploads/violations/";
    private static final long MAX_FILE_SIZE = 1048576; // 1MB
    private static final List<String> ALLOWED_TYPES = List.of("image/jpeg", "image/png");

    public String storeImage(MultipartFile file) throws IOException {
        // Validar tipo do arquivo
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new IllegalArgumentException("Picture must be JPEG or PNG");
        }

        // Validar tamanho do arquivo
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("Picture size exceeds 1MB");
        }

        // Criar diretório se não existir
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Gerar nome único para o arquivo
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        // Salvar o arquivo
        Files.write(filePath, file.getBytes());

        // Gerar URL fictícia
        return "http://localhost:8080/uploads/violations/" + fileName;
    }
}