package is.symphony.collegeinternship.olympicgames.services.impl;
import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import is.symphony.collegeinternship.olympicgames.exceptions.ResourceNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.dto.AthleteDTO;
import is.symphony.collegeinternship.olympicgames.repositories.AthleteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class AthleteService {

    private static Logger LOGGER = LoggerFactory.getLogger(AthleteService.class);

    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private DTOConverterService dtoConverterService;
    @Autowired
    private CountryService countryService;


    public void save(Athlete athlete) throws ElementExistsException {
        athlete.setCountry(countryService.findByCountryShortCode(athlete.getNationality()));
        Athlete save = athleteRepository.save(athlete);
        LOGGER.info("Saved {}", save);
    }

    public List<AthleteDTO> findAllDTO() throws ResourceNotFoundException {
        LOGGER.info("Accessing DB to get all athletes...");

        List<Athlete> allAthletes = athleteRepository.findAll();
        LOGGER.info("Found {} athletes!", allAthletes.size());
        List<AthleteDTO> allAthletesList = allAthletes.stream().map(dtoConverterService::convertToDTO).collect(Collectors.toList());
        if (allAthletes.isEmpty()) {
            LOGGER.error("Could not find any athlete!");
            throw new ResourceNotFoundException();
        }
        else return allAthletesList;
    }

    public AthleteDTO findDTOByBadgeNumber(String badge_number) throws NoSuchElementException {
        try{
            LOGGER.info("Accessing DB to get an athlete...");
            Athlete found = athleteRepository.findByBadgeNumber(badge_number);
            LOGGER.info("Athlete found...");
            return dtoConverterService.convertToDTO(found);
        } catch (Exception e){
            LOGGER.error("Athlete not found...");
            throw new NoSuchElementException();
        }
    }

    public Athlete findByBadgeNumber(String badge_number){
        LOGGER.info("Accessing DB to get an athlete...");
        Athlete found = athleteRepository.findByBadgeNumber(badge_number);
        LOGGER.info("Athlete found...");
        return found;
    }


    public void updateAthlete(Athlete athlete) {
        LOGGER.info("Updating an athlete...");
        athlete.setCountry(countryService.findByCountryShortCode(athlete.getNationality()));
        athleteRepository.save(athlete);
        LOGGER.info("Athlete updated...");
    }

    public void delete(String badge_number) {
        LOGGER.info("Deleting an athlete...");
        athleteRepository.delete(findByBadgeNumber(badge_number));
        LOGGER.info("Athlete deleted..");
    }
}