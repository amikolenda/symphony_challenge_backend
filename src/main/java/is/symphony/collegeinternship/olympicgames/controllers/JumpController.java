package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.dto.JumpDTO;
import is.symphony.collegeinternship.olympicgames.services.impl.JumpService;
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
@RequestMapping("/api/competitions/start")
public class JumpController {
    private static Logger LOGGER = LoggerFactory.getLogger(JumpController.class);
    @Autowired
    private JumpService jumpService;

    @PostMapping
    private ResponseEntity<JumpDTO> createJump(@RequestBody @Valid JumpDTO jumpDTO) throws ElementNotFoundException, ElementExistsException {
        LOGGER.info("Saving jump...");
        return ResponseEntity.ok().body(jumpService.saveDTO(jumpDTO));
    }

    @GetMapping("/jumps")
    public ResponseEntity<List<JumpDTO>> showJumps() {
        LOGGER.info("Listing jumps...");
        return ResponseEntity.ok().body(jumpService.findAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JumpDTO> showJump(@PathVariable("id") Long id) throws ElementNotFoundException {
        LOGGER.info("Listing a jump...");
        return ResponseEntity.ok().body(jumpService.findDTOById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<JumpDTO> updateJump(@PathVariable("id") Long id, @RequestBody @Valid JumpDTO jumpDTO) throws ElementNotFoundException {
        LOGGER.info("Updating a jump...");
        return ResponseEntity.ok().body(jumpService.update(jumpDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJump(@PathVariable("id") Long id) throws ElementNotFoundException {
        LOGGER.info("Deleting a jump...");
        jumpService.delete(id);
        return ResponseEntity.ok().body("Jump with an id: " + id + "is deleted");
    }

}
