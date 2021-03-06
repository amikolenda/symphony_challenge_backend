package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.dto.SportDTO;
import is.symphony.collegeinternship.olympicgames.services.impl.SportService;
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
@RequestMapping("/api/sports")
public class SportController {
    @Autowired
    SportService sportService;

    @GetMapping
    public ResponseEntity<List<SportDTO>> showSports() {
        return ResponseEntity.ok().body(sportService.findAllDTO());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SportDTO> showSport(@PathVariable("id") Long id) throws ElementNotFoundException {
        return ResponseEntity.ok().body(sportService.findDTOById(id));
    }

    @PostMapping
    public ResponseEntity<SportDTO> createSport(@RequestBody @Valid SportDTO sportDTO) throws ElementExistsException, ElementNotFoundException {
        return ResponseEntity.ok().body(sportService.save(sportDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SportDTO> updateSport(@PathVariable("id") Long id, @RequestBody @Valid SportDTO sportDTO) throws ElementNotFoundException {
        return ResponseEntity.ok().body(sportService.updateSport(sportDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSport(@PathVariable("id") Long id) throws ElementNotFoundException {
        sportService.delete(id);
        return ResponseEntity.ok().body("Sport " + id + "is deleted!");
    }




}
