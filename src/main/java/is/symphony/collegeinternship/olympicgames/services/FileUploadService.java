package is.symphony.collegeinternship.olympicgames.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import is.symphony.collegeinternship.olympicgames.exceptions.BadExtensionException;
import is.symphony.collegeinternship.olympicgames.exceptions.NoFileException;

import java.io.IOException;
import java.io.Reader;

public interface FileUploadService {
    public ResponseEntity<String> uploadFile(MultipartFile file) throws  NoFileException, BadExtensionException;
    public ResponseEntity<String> readFile(Reader inputStreamReader) throws IOException;
}
