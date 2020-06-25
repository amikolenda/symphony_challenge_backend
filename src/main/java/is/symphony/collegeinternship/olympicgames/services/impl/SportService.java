package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import is.symphony.collegeinternship.olympicgames.models.Sport;
import is.symphony.collegeinternship.olympicgames.models.dto.SportDTO;
import is.symphony.collegeinternship.olympicgames.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SportService {
    private static Logger LOGGER = LoggerFactory.getLogger(SportService.class);

    @Autowired
    SportRepository sportRepository;

    @Autowired
    DTOConverterService dtoConverterService;

    @Autowired
    CountryService countryService;

    @Autowired
    AthleteService athleteService;


    public SportDTO save(SportDTO sportDTO) throws ElementExistsException, ElementNotFoundException {
        if (sportRepository.existsByName(sportDTO.getName())) {
            LOGGER.error("Sport already exists!");
            throw new ElementExistsException();
        }
        LOGGER.info("Saving sport...");
        Sport sport = dtoConverterService.convertSportDTOToDAO(sportDTO);

        Set<Athlete> athletesSet = sport.getAthletes();
        if (athletesSet != null) sport.setAthletes(athleteService.setAthletes(athletesSet));

        LOGGER.info("Saving sport...");
        Sport save = sportRepository.save(sport);
        LOGGER.info("Saved {}", save);
        return dtoConverterService.convertSportDTO(save);
    }
    public Sport save(Sport sport){
        if (sportRepository.existsByName(sport.getName())) {
            Sport existingSport = sportRepository.findSportByName(sport.getName());
            sport.setId(existingSport.getId());
        }
        LOGGER.info("Saving/updating sport...");
        sportRepository.save(sport);
        LOGGER.info("Saved/updated {}", sport);
        return sport;
    }

    public List<SportDTO> findAllDTO() throws ElementNotFoundException {
        LOGGER.info("Accessing DB to get all sports...");
        List<Sport> allSports = sportRepository.findAll();
        LOGGER.info("Found {} sports!", allSports.size());

        List<SportDTO> allSportsList = sportRepository.findAll().stream().map(dtoConverterService::convertSportDTO).collect(Collectors.toList());
        if (allSportsList.isEmpty()){
            LOGGER.error("Could not find any sport!");
            throw new ElementNotFoundException();
        }
        else return allSportsList;
    }
    public SportDTO findDTOByName(String name) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get a sport...");
            Sport found = sportRepository.findSportByName(name);
            LOGGER.info("Sport found.");
            return dtoConverterService.convertSportDTO(found);
        } catch (Exception e){
            LOGGER.info("Sport not found.");
            throw new ElementNotFoundException();
        }
    }
    public Sport findByName(String name) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get a sport...");
            Sport found = sportRepository.findSportByName(name);
            LOGGER.info("Sport found.");
            return found;
        } catch (Exception e){
            LOGGER.error("Sport not found.");
            throw new ElementNotFoundException();
        }
    }

    public void delete(String name) throws ElementNotFoundException {
        LOGGER.info("Accessing DB to get a sport...");
        Sport sport = findByName(name);
        LOGGER.info("Deleting sport...");
        sportRepository.delete(sport);
        LOGGER.info("Sport deleted.");
    }

    public SportDTO updateSport(SportDTO sportDTO, String name) throws ElementNotFoundException {
        if (!sportRepository.existsByName(name)) {
            LOGGER.error("Sport does not exist!");
            throw new ElementNotFoundException();
        }
        LOGGER.info("Updating sport...");
        Sport newSport = dtoConverterService.convertSportDTOToDAO(sportDTO);
        Sport existingSport = sportRepository.findSportByName(name);
        newSport.setId(existingSport.getId());
        Set<Athlete> athletesSet = newSport.getAthletes();
        if (athletesSet != null) newSport.setAthletes(athleteService.setAthletes(athletesSet));

        sportRepository.save(newSport);
        LOGGER.info("Updated {}", newSport);
        return dtoConverterService.convertSportDTO(newSport);
    }

}
