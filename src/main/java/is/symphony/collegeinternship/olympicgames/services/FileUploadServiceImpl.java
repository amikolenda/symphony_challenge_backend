package is.symphony.collegeinternship.olympicgames.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService{
    @Autowired
    private AthleteService athleteService;

    public static String getFileExtension(MultipartFile file) {
        String orgName = file.getOriginalFilename();
        assert orgName != null;
        String[] parts = orgName.split("\\.");
        return parts[parts.length-1];
    }

    private boolean validateFileExtension(String extension){
        return extension.equals(".txt") || extension.equals(".json") || extension.equals(".csv");
    }

    private ResponseEntity<String> readFile(Reader inputStreamReader) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Athlete> athletes = mapper.readValue(inputStreamReader, new TypeReference<List<Athlete>>() {});
        return athleteService.athleteValidationAdd(athletes);
    }

    @Override
    public ResponseEntity<String> uploadFile(MultipartFile file) {

        if (file.isEmpty()) {
            return new ResponseEntity<String>("File is empty. Please select a file to upload.", HttpStatus.BAD_REQUEST);
        } else if(validateFileExtension(getFileExtension(file))) {
            return new ResponseEntity<String>("Invalid file extension. Please select a valid file to upload.", HttpStatus.BAD_REQUEST);
        } else {
            try (Reader inputStreamReader = new InputStreamReader(file.getInputStream())) {
                return readFile(inputStreamReader);
            } catch (IOException e) {
                final Throwable cause = e.getCause() == null ? e : e.getCause();
                if (cause instanceof UnrecognizedPropertyException) {
                    String exceptionResponse = String.format("Invalid field name: %s\n", e.getMessage());
                    return new ResponseEntity<String>(exceptionResponse, HttpStatus.BAD_REQUEST);
                } else {
                    e.printStackTrace();
                    return new ResponseEntity<String>("An error occurred while processing the file.", HttpStatus.BAD_REQUEST);
                }

            }
        }

    }
}
