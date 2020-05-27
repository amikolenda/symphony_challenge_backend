package is.symphony.collegeinternship.olympicgames.services;

import org.springframework.http.ResponseEntity;
import is.symphony.collegeinternship.olympicgames.models.Athlete;

import javax.validation.Valid;
import java.util.List;

public interface AthleteUploadService {
    public ResponseEntity<String> athleteValidationAdd(List<@Valid Athlete> athletes);
    public void updateAthlete(List<Athlete> athletes);
    public void updateFields(Athlete athlete);

}
