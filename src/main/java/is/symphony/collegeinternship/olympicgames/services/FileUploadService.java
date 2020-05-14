package is.symphony.collegeinternship.olympicgames.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    public ResponseEntity<String> uploadFile(MultipartFile file);
}
