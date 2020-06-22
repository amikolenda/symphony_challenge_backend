package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import is.symphony.collegeinternship.olympicgames.models.Sport;
import is.symphony.collegeinternship.olympicgames.models.dto.SportDTO;
import is.symphony.collegeinternship.olympicgames.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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


    public SportDTO save(SportDTO sportDTO) throws ElementExistsException {
        if (sportRepository.existsByName(sportDTO.getName())) {
            LOGGER.error("Sport already exists!");
            throw new ElementExistsException();
        }
        LOGGER.info("Saving sport...");
        Sport sport = dtoConverterService.convertSportDTOToDAO(sportDTO);
        Sport save = sportRepository.save(sport);
        LOGGER.info("Saved {}", save);
        return dtoConverterService.convertSportDTO(save);
    }

    public List<SportDTO> findAllDTO() throws NoSuchElementException {
        LOGGER.info("Accessing DB to get all sports...");
        List<Sport> allSports = sportRepository.findAll();
        LOGGER.info("Found {} sports!", allSports.size());

        List<SportDTO> allSportsList = sportRepository.findAll().stream().map(dtoConverterService::convertSportDTO).collect(Collectors.toList());
        if (allSportsList.isEmpty()){
            LOGGER.error("Could not find any sport!");
            throw new NoSuchElementException();
        }
        else return allSportsList;
    }
    public SportDTO findDTOByName(String name) throws NoSuchElementException {
        try{
            LOGGER.info("Accessing DB to get a sport...");
            Sport found = sportRepository.findSportByName(name);
            LOGGER.info("Sport found.");
            return dtoConverterService.convertSportDTO(found);
        } catch (Exception e){
            LOGGER.info("Sport not found.");
            throw new NoSuchElementException();
        }
    }
    public Sport findByName(String name) throws NoSuchElementException {
        try{
            LOGGER.info("Accessing DB to get a sport...");
            Sport found = sportRepository.findSportByName(name);
            LOGGER.info("Sport found.");
            return found;
        } catch (Exception e){
            LOGGER.error("Sport not found.");
            throw new NoSuchElementException();
        }
    }

    public void delete(String name) throws NoSuchElementException {
        LOGGER.info("Accessing DB to get a sport...");
        Sport sport = findByName(name);
        LOGGER.info("Deleting sport...");
        sportRepository.delete(sport);
        LOGGER.info("Sport deleted.");
    }

    public SportDTO updateSport(SportDTO sportDTO) throws NoSuchElementException {
        if (!sportRepository.existsByName(sportDTO.getName())) {
            LOGGER.error("Sport does not exist!");
            throw new NoSuchElementException();
        }
        LOGGER.info("Updating sport...");
        Sport update = sportRepository.save(dtoConverterService.convertSportDTOToDAO(sportDTO));
        LOGGER.info("Updated {}", update);
        return dtoConverterService.convertSportDTO(update);

    }

}
