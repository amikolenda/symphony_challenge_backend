package is.symphony.collegeinternship.olympicgames.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.repositories.AthleteRepository;
import is.symphony.collegeinternship.olympicgames.services.AthleteService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UploadController {
    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private AthleteService athleteService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){

        ObjectMapper mapper = new ObjectMapper();

        if (file.isEmpty()) {
            return new ResponseEntity<String>("File is empty. Please select a file to upload.", HttpStatus.BAD_REQUEST);
        } else {
            try (Reader inputStreamReader = new InputStreamReader(file.getInputStream())) {

                List<Athlete> athletes = mapper.readValue(inputStreamReader, new TypeReference<List<Athlete>>() {});
                return athleteService.athleteValidationAdd(athletes);

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


    @GetMapping("/athletes")
    public List<Athlete> showAthletes(){

        return athleteRepository.findAll();
    }

    @GetMapping("/athletes/{badge_number}")
    public Optional<Athlete> getAthleteById(@PathVariable(value = "badge_number") String badge_number){

        return athleteRepository.findById(badge_number);
    }


}
