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
                    if (athleteRepository.existsById(athlete.getBadge_number())) updateFields(athlete);
                    else athleteService.save(athlete);
                });
    }

    @Override
    public void updateFields(Athlete athlete){
        Athlete existingAthlete = athleteRepository.findById(athlete.getBadge_number()).get();
        if (athlete.getFirst_name() != null) existingAthlete.setFirst_name(athlete.getFirst_name());
        if (athlete.getLast_name() != null) existingAthlete.setLast_name(athlete.getLast_name());
        if (athlete.getDate_of_birth() != null) existingAthlete.setDate_of_birth(athlete.getDate_of_birth());
        if (athlete.getNationality() != null) existingAthlete.setNationality(athlete.getNationality());
        if (athlete.getPhoto() != null) existingAthlete.setPhoto(athlete.getPhoto());
        if (athlete.getGender() != null) existingAthlete.setGender(athlete.getGender());
        athleteRepository.save(existingAthlete);
    }
}
