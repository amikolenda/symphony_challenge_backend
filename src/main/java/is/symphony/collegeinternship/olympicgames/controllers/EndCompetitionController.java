package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.dto.CompetitionDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.JumpDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.ResultDTO;
import is.symphony.collegeinternship.olympicgames.services.impl.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/competitions/end")
public class EndCompetitionController {
    private static Logger LOGGER = LoggerFactory.getLogger(EndCompetitionController.class);
    @Autowired
    private ResultService resultService;

    @GetMapping
    public ResponseEntity<List<ResultDTO>> showResults(@RequestBody @Valid CompetitionDTO competitionDTO) {
        LOGGER.info("Listing final results...");
        return ResponseEntity.ok().body(resultService.showFinalResults(competitionDTO));
    }

    @GetMapping("/{resultId}")
    public ResponseEntity<ResultDTO> showResult(@PathVariable("resultId") Long resultId) throws ElementNotFoundException {
        LOGGER.info("Listing a result...");
        return ResponseEntity.ok().body(resultService.findDTOById(resultId));
    }


}
