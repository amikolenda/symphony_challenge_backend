package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.exceptions.NoSuchElementException;
import is.symphony.collegeinternship.olympicgames.exceptions.ResourceNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.dto.AthleteDTO;
import is.symphony.collegeinternship.olympicgames.services.impl.AthleteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {
    private static Logger LOGGER = LoggerFactory.getLogger(AthleteController.class);

    @Autowired
    private AthleteService athleteService;

    @GetMapping
    public ResponseEntity<List<AthleteDTO>> showAthletes() throws ResourceNotFoundException {
        LOGGER.info("Listing athletes...");
        return ResponseEntity.ok().body(athleteService.findAllDTO());
    }

    @GetMapping("/{badge_number}")
    public ResponseEntity<AthleteDTO> showAthlete(@PathVariable("badge_number") String badge_number) throws NoSuchElementException {
        LOGGER.info("Listing an athlete...");
        return ResponseEntity.ok().body(athleteService.findDTOByBadgeNumber(badge_number));
    }
    @PutMapping("/{badge_number}")
    public ResponseEntity<AthleteDTO> updateAthlete(@PathVariable("badge_number") String badge_number, @RequestBody @Valid Athlete athlete) throws NoSuchElementException {
        LOGGER.info("Updating an athlete...");
        athleteService.updateAthlete(athlete);
        return ResponseEntity.ok().body(athleteService.findDTOByBadgeNumber(badge_number));
    }

    @DeleteMapping("/{badge_number}")
    public ResponseEntity<String> deleteAthlete(@PathVariable("badge_number") String badge_number) throws NoSuchElementException {
        LOGGER.info("Deleting an athlete...");
        athleteService.delete(badge_number);
        return ResponseEntity.ok().body("Athlete with badge number: " + badge_number + "is deleted");
    }

}
