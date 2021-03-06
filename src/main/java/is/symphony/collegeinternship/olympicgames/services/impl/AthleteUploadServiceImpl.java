package is.symphony.collegeinternship.olympicgames.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger LOGGER = LoggerFactory.getLogger(AthleteUploadServiceImpl.class);

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
                    LOGGER.info("Storing athlete {}", athlete);
                    if (athleteRepository.existsByBadgeNumber(athlete.getBadgeNumber())) updateFields(athlete);
                    else athleteService.save(athlete);
                });
    }

    @Override
    public void updateFields(Athlete athlete){
        Athlete existingAthlete = athleteRepository.findByBadgeNumber(athlete.getBadgeNumber());
        if (athlete.getFirstName() != null) existingAthlete.setFirstName(athlete.getFirstName());
        if (athlete.getLastName() != null) existingAthlete.setLastName(athlete.getLastName());
        if (athlete.getDateOfBirth() != null) existingAthlete.setDateOfBirth(athlete.getDateOfBirth());
        if (athlete.getNationality() != null) existingAthlete.setNationality(athlete.getNationality());
        if (athlete.getPhoto() != null) existingAthlete.setPhoto(athlete.getPhoto());
        if (athlete.getGender() != null) existingAthlete.setGender(athlete.getGender());
        Athlete save = athleteRepository.save(existingAthlete);
        LOGGER.info("Stored: {}", athlete);
    }
}
