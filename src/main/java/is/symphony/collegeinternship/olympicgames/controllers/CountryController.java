package is.symphony.collegeinternship.olympicgames.controllers;

import is.symphony.collegeinternship.olympicgames.models.dto.CountryDTO;
import is.symphony.collegeinternship.olympicgames.services.impl.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private static Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryDTO>> showCountries() {
        LOGGER.info("Listing all countries...");
        return ResponseEntity.ok().body(countryService.findAllDTO());
    }


}
