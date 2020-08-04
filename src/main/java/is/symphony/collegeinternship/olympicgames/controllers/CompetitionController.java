package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.dto.AthleteDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.CompetitionDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.SportDTO;
import is.symphony.collegeinternship.olympicgames.services.impl.AthleteService;
import is.symphony.collegeinternship.olympicgames.services.impl.CompetitionService;
import is.symphony.collegeinternship.olympicgames.services.impl.SportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {
    private static Logger LOGGER = LoggerFactory.getLogger(CompetitionController.class);

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private AthleteService athleteService;

    @Autowired
    private SportService sportService;

    @PostMapping
    public ResponseEntity<CompetitionDTO> createCompetition(@RequestBody @Valid CompetitionDTO competitionDTO) throws ElementExistsException, ElementNotFoundException {
        return ResponseEntity.ok().body(competitionService.save(competitionDTO));
    }

    @GetMapping
    public ResponseEntity<List<CompetitionDTO>> showCompetitions() {
        LOGGER.info("Listing competitions...");
        return ResponseEntity.ok().body(competitionService.findAllDTO());
    }

    @GetMapping("/athletes")
    public ResponseEntity<List<AthleteDTO>> showAthletes() {
        LOGGER.info("Listing athletes...");
        return ResponseEntity.ok().body(athleteService.findAllDTO());
    }
    @GetMapping("/athletes/{id}")
    public ResponseEntity<List<CompetitionDTO>> showCompetitionByAthlete(@PathVariable("id") Long id) throws ElementNotFoundException {
        LOGGER.info("Listing a competition...");
        return ResponseEntity.ok().body(competitionService.findDTOByAthlete(id));
    }

    @GetMapping("/sports")
    public ResponseEntity<List<SportDTO>> showSports() {
        return ResponseEntity.ok().body(sportService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDTO> showCompetition(@PathVariable("id") Long id) throws ElementNotFoundException {
        LOGGER.info("Listing a competition...");
        return ResponseEntity.ok().body(competitionService.findDTOById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CompetitionDTO> updateCompetition(@PathVariable("id") Long id, @RequestBody @Valid CompetitionDTO competitionDTO) throws ElementNotFoundException {
        LOGGER.info("Updating a competition...");
        return ResponseEntity.ok().body(competitionService.update(competitionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompetition(@PathVariable("id") Long id) throws ElementNotFoundException {
        LOGGER.info("Deleting a competition...");
        competitionService.delete(id);
        return ResponseEntity.ok().body("Competition with an id: " + id + "is deleted");
    }


}
