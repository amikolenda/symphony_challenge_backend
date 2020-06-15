package is.symphony.collegeinternship.olympicgames.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import is.symphony.collegeinternship.olympicgames.exceptions.InvalidFieldNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceImpl.class);


    @Autowired
    private AthleteUploadServiceImpl athleteUploadServiceImpl;

    @Override
    public ResponseEntity<String> uploadFile(MultipartFile multipartFile) throws InvalidFieldNameException, NoFileException, BadExtensionException {
        LOGGER.info("Uploading file...");
        validateFileIsEmpty(multipartFile);
        validateFileExtension(multipartFile);
        if (multipartFile.isEmpty()) {
            LOGGER.error("Something went wrong!");
            throw new NoFileException();
        }

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
        LOGGER.info("Mapping athlete");
        ObjectMapper mapper = new ObjectMapper();
        List<Athlete> athletes = mapper.readValue(inputStreamReader, new TypeReference<List<Athlete>>() {
        });
        LOGGER.info("Read {} athletes from file!", athletes.size());
        return athleteUploadServiceImpl.athleteValidationAdd(athletes);
    }

}
