package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.dto.AthleteDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.SportDTO;
import is.symphony.collegeinternship.olympicgames.services.impl.AthleteService;
import is.symphony.collegeinternship.olympicgames.services.impl.SportService;
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
    @Autowired
    private SportService sportService;

    @GetMapping
    public ResponseEntity<List<AthleteDTO>> showAthletes() {
        LOGGER.info("Listing athletes...");
        return ResponseEntity.ok().body(athleteService.findAllDTO());
    }

    @GetMapping("/sports")
    public ResponseEntity<List<SportDTO>> showSports() {
        return ResponseEntity.ok().body(sportService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AthleteDTO> showAthlete(@PathVariable("id") Long id) throws ElementNotFoundException {
        LOGGER.info("Listing an athlete...");
        return ResponseEntity.ok().body(athleteService.findDTOById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AthleteDTO> updateAthlete(@PathVariable("id") Long id, @RequestBody @Valid AthleteDTO athleteDTO) throws ElementNotFoundException {
        LOGGER.info("Updating an athlete...");
        return ResponseEntity.ok().body(athleteService.updateAthlete(athleteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAthlete(@PathVariable("id") Long id) throws ElementNotFoundException {
        LOGGER.info("Deleting an athlete...");
        athleteService.delete(id);
        return ResponseEntity.ok().body("Athlete with id number: " + id + "is deleted");
    }

}
