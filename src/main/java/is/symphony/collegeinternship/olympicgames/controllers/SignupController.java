package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.models.Volunteer;
import is.symphony.collegeinternship.olympicgames.models.dto.VolunteerDTO;
import is.symphony.collegeinternship.olympicgames.services.impl.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    VolunteerService volunteerService;

    @PostMapping
    public ResponseEntity<VolunteerDTO> createVolunteer(@RequestBody @Valid Volunteer volunteer){
        volunteerService.save(volunteer);
        return ResponseEntity.ok().body(volunteerService.findDTOById(volunteer.getUserName()));
    }
}
