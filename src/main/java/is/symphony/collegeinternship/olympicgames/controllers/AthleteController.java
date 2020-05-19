package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.exceptions.NoSuchElementException;
import is.symphony.collegeinternship.olympicgames.exceptions.ResourceNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.dto.AthleteDTO;
import is.symphony.collegeinternship.olympicgames.services.impl.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @GetMapping
    public ResponseEntity<List<AthleteDTO>> showAthletes() throws ResourceNotFoundException {
        return ResponseEntity.ok().body(athleteService.findAll());
    }
    @GetMapping("/{badge_number}")
    public ResponseEntity<AthleteDTO> showAthlete(@PathVariable("badge_number") String badge_number) throws NoSuchElementException {
        return ResponseEntity.ok().body(athleteService.findById(badge_number));
    }

}
