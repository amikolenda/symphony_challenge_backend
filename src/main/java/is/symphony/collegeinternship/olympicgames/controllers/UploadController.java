package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.services.FileUploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UploadController {
    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private FileUploadServiceImpl fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){

        return fileUploadService.uploadFile(file);
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
