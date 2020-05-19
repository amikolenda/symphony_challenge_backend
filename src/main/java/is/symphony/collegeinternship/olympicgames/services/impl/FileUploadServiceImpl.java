package is.symphony.collegeinternship.olympicgames.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import is.symphony.collegeinternship.olympicgames.exceptions.InvalidFieldNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import is.symphony.collegeinternship.olympicgames.exceptions.BadExtensionException;
import is.symphony.collegeinternship.olympicgames.exceptions.NoFileException;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.services.FileUploadService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import static is.symphony.collegeinternship.olympicgames.utils.FileUtility.validateFileExtension;
import static is.symphony.collegeinternship.olympicgames.utils.FileUtility.validateFileIsEmpty;


@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private AthleteUploadServiceImpl athleteUploadServiceImpl;

    @Override
    public ResponseEntity<String> uploadFile(MultipartFile multipartFile) throws InvalidFieldNameException, NoFileException, BadExtensionException {
        validateFileIsEmpty(multipartFile);
        validateFileExtension(multipartFile);
        if (multipartFile.isEmpty())throw new NoFileException();

        try (Reader inputStreamReader = new InputStreamReader(multipartFile.getInputStream())) {
            return readFile(inputStreamReader);
        } catch (UnrecognizedPropertyException e){
            throw new InvalidFieldNameException();
        } catch (Exception e) {
            String exceptionResponse = String.format("An error occurred while processing the file: %s\n", e.getMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        }

    }
    @Override
    public ResponseEntity<String> readFile(Reader inputStreamReader) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Athlete> athletes = mapper.readValue(inputStreamReader, new TypeReference<List<Athlete>>() {
        });
        return athleteUploadServiceImpl.athleteValidationAdd(athletes);
    }

}
