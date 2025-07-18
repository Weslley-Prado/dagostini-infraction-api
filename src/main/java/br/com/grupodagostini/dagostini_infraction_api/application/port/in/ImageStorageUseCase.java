package br.com.grupodagostini.dagostini_infraction_api.application.port.in;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStorageUseCase {
    String storeImage(MultipartFile file) throws IOException;
}
