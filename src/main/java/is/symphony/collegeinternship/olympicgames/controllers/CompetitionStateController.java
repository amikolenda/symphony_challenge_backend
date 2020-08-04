package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.dto.CompetitionDTO;
import is.symphony.collegeinternship.olympicgames.services.impl.CompetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/competitions/all")
public class CompetitionStateController {
    private static Logger LOGGER = LoggerFactory.getLogger(CompetitionStateController.class);

    @Autowired
    private CompetitionService competitionService;

    @GetMapping("/{state}")
    public ResponseEntity<List<CompetitionDTO>> showCompetitionsByState(@PathVariable("state") String state) throws ElementNotFoundException {
        LOGGER.info("Listing competitions by state...");
        return ResponseEntity.ok().body(competitionService.findAllDTOByState(state));
    }
}
