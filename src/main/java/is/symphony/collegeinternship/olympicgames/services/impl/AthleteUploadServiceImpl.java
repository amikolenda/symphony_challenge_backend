package is.symphony.collegeinternship.olympicgames.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.repositories.AthleteRepository;
import is.symphony.collegeinternship.olympicgames.services.AthleteUploadService;

import javax.validation.Valid;
import java.util.List;

@Validated
@Service
public class AthleteUploadServiceImpl implements AthleteUploadService {
    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private AthleteService athleteService;

    @Override
    public ResponseEntity<String> athleteValidationAdd(List<@Valid Athlete> athletes){
        updateAthlete(athletes);
        return new ResponseEntity<>("Athletes uploaded/updated.", HttpStatus.OK);

    }

    @Override
    public void updateAthlete(List<Athlete> athletes) {
        athletes.stream()
                .forEach(athlete -> {
                    if (athleteRepository.existsById(athlete.getBadgeNumber())) updateFields(athlete);
                    else athleteService.save(athlete);
                });
    }

    @Override
    public void updateFields(Athlete athlete){
        Athlete existingAthlete = athleteRepository.findById(athlete.getBadgeNumber()).get();
        if (athlete.getFirstName() != null) existingAthlete.setFirstName(athlete.getFirstName());
        if (athlete.getLastName() != null) existingAthlete.setLastName(athlete.getLastName());
        if (athlete.getDateOfBirth() != null) existingAthlete.setDateOfBirth(athlete.getDateOfBirth());
        if (athlete.getNationality() != null) existingAthlete.setNationality(athlete.getNationality());
        if (athlete.getPhoto() != null) existingAthlete.setPhoto(athlete.getPhoto());
        if (athlete.getGender() != null) existingAthlete.setGender(athlete.getGender());
        athleteRepository.save(existingAthlete);
    }
}
