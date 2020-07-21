package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.dto.CountryDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.SportDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.VolunteerDTO;
import is.symphony.collegeinternship.olympicgames.services.impl.CountryService;
import is.symphony.collegeinternship.olympicgames.services.impl.SportService;
import is.symphony.collegeinternship.olympicgames.services.impl.VolunteerService;
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
@RequestMapping("/api/volunteers")
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;
    @Autowired
    private SportService sportService;

    @GetMapping
    public ResponseEntity<List<VolunteerDTO>> showVolunteers() {
        return ResponseEntity.ok().body(volunteerService.findAllDTO());
    }
    @GetMapping("/{id}")
    public ResponseEntity<VolunteerDTO> showVolunteer(@PathVariable("id") Long id) throws ElementNotFoundException {
        return ResponseEntity.ok().body(volunteerService.findDTOById(id));
    }

    @GetMapping("/sports")
    public ResponseEntity<List<SportDTO>> showSports() {
        return ResponseEntity.ok().body(sportService.findAllDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolunteerDTO> updateVolunteer(@PathVariable("id") Long id, @RequestBody @Valid VolunteerDTO volunteerDTO) throws ElementNotFoundException{
        return ResponseEntity.ok().body(volunteerService.updateVolunteer(volunteerDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVolunteer(@PathVariable("id") Long id) throws ElementNotFoundException {
        volunteerService.delete(id);
        return ResponseEntity.ok().body("Volunteer with id: " + id + "is deleted");
    }

}
