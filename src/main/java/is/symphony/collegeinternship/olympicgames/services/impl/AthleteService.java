package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
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


    public AthleteDTO save(AthleteDTO athleteDTO) throws ElementExistsException {
        if (athleteRepository.existsByBadgeNumber(athleteDTO.getBadgeNumber())) {
            LOGGER.error("Athlete already exists!");
            throw new ElementExistsException();
        }
        LOGGER.info("Saving athlete...");
        Athlete athlete = dtoConverterService.convertAthleteDTOToDAO(athleteDTO);
        athlete.setCountry(countryService.findByCountryShortCode(athlete.getNationality()));
        Athlete save = athleteRepository.save(athlete);
        LOGGER.info("Saved {}", save);
        return dtoConverterService.convertToDTO(save);
    }

    public Athlete save(Athlete athlete) {
        if (athleteRepository.existsByBadgeNumber(athlete.getBadgeNumber())) {
            Athlete existingAthlete = athleteRepository.findByBadgeNumber(athlete.getBadgeNumber());
            athlete.setId(existingAthlete.getId());
        }
        LOGGER.info("Saving/updating athlete...");
        athlete.setCountry(countryService.findByCountryShortCode(athlete.getNationality()));
        Athlete save = athleteRepository.save(athlete);
        LOGGER.info("Saved/updated {}", save);
        return save;
    }

    public List<AthleteDTO> findAllDTO() throws ElementNotFoundException {
        LOGGER.info("Accessing DB to get all athletes...");

        List<Athlete> allAthletes = athleteRepository.findAll();
        LOGGER.info("Found {} athletes!", allAthletes.size());
        List<AthleteDTO> allAthletesList = allAthletes.stream().map(dtoConverterService::convertToDTO).collect(Collectors.toList());
        if (allAthletes.isEmpty()) {
            LOGGER.error("Could not find any athlete!");
            throw new ElementNotFoundException();
        }
        else return allAthletesList;
    }

    public AthleteDTO findDTOByBadgeNumber(String badge_number) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get an athlete...");
            Athlete found = athleteRepository.findByBadgeNumber(badge_number);
            LOGGER.info("Found {}", found);
            return dtoConverterService.convertToDTO(found);
        } catch (Exception e){
            LOGGER.error("Athlete not found.");
            throw new ElementNotFoundException();
        }
    }

    public Athlete findByBadgeNumber(String badge_number) throws ElementNotFoundException{
        if (!athleteRepository.existsByBadgeNumber(badge_number)) {
            LOGGER.error("Athlete does not exist!");
            throw new ElementNotFoundException();
        }
        LOGGER.info("Accessing DB to get an athlete...");
        Athlete found = athleteRepository.findByBadgeNumber(badge_number);
        LOGGER.info("Found {}", found);
        return found;
    }


    public AthleteDTO updateAthlete(AthleteDTO athleteDTO) throws ElementNotFoundException{
        if (!athleteRepository.existsByBadgeNumber(athleteDTO.getBadgeNumber())) {
            LOGGER.error("Athlete does not exist!");
            throw new ElementNotFoundException();
        }
        LOGGER.info("Updating an athlete...");
        Athlete newAthlete = dtoConverterService.convertAthleteDTOToDAO(athleteDTO);
        Athlete existingAthlete = athleteRepository.findByBadgeNumber(newAthlete.getBadgeNumber());
        newAthlete.setId(existingAthlete.getId());
        newAthlete.setCountry(countryService.findByCountryShortCode(newAthlete.getNationality()));
        athleteRepository.save(newAthlete);
        LOGGER.info("Updated {}", newAthlete);
        return dtoConverterService.convertToDTO(newAthlete);
    }

    public void delete(String badge_number) throws ElementNotFoundException{
        LOGGER.info("Accessing DB to get an athlete...");
        Athlete athlete = findByBadgeNumber(badge_number);
        LOGGER.info("Deleting an athlete...");
        athleteRepository.delete(athlete);
        LOGGER.info("Athlete deleted.");
    }
}