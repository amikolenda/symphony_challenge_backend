package is.symphony.collegeinternship.olympicgames.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.repositories.AthleteRepository;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class AthleteService {
    @Autowired
    private AthleteRepository athleteRepository;

    public Iterable<Athlete> list() {
        return athleteRepository.findAll();
    }

    public void save(Athlete athlete) {
        athleteRepository.save(athlete);
    }

    public void saveAll(List<Athlete> athletes) {
        for (Athlete athlete:athletes) athleteRepository.save(athlete);
    }

    public ResponseEntity<String> athleteValidationAdd(List<@Valid Athlete> athletes){
        for (Athlete athlete:athletes) {

            athleteRepository.save(athlete);
        }
        return new ResponseEntity<String>("Athletes uploaded.", HttpStatus.OK);

    }

}
