package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.exceptions.NoSuchElementException;
import is.symphony.collegeinternship.olympicgames.exceptions.ResourceNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Volunteer;
import is.symphony.collegeinternship.olympicgames.models.dto.VolunteerDTO;
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
    VolunteerService volunteerService;
    @GetMapping
    public ResponseEntity<List<VolunteerDTO>> showVolunteers() throws ResourceNotFoundException {
        return ResponseEntity.ok().body(volunteerService.findAllDTO());
    }
    @GetMapping("/{userName}")
    public ResponseEntity<VolunteerDTO> showVolunteer(@PathVariable("userName") String userName) throws NoSuchElementException {
        return ResponseEntity.ok().body(volunteerService.findDTOById(userName));
    }

    @PutMapping("/{userName}")
    public ResponseEntity<VolunteerDTO> updateVolunteer(@PathVariable("userName") String userName, @RequestBody @Valid Volunteer volunteer){
        Volunteer existingVolunteer = volunteerService.findById(userName);
        volunteerService.updateVolunteer(existingVolunteer, volunteer);
        return ResponseEntity.ok().body(volunteerService.findDTOById(userName));
    }
    @DeleteMapping("/{userName}")
    public ResponseEntity<String> deleteVolunteer(@PathVariable("userName") String userName) throws NoSuchElementException {
        volunteerService.delete(userName);
        return ResponseEntity.ok().body("Volunteer with user name: " + userName + "is deleted");
    }

}
