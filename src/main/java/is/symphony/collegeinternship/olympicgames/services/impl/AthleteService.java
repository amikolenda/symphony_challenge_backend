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
        athlete.setCountry(countryService.findById(athlete.getNationality()).get());
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
    public AthleteDTO findDTOById(String badge_number) throws NoSuchElementException {
        try{
            return dtoConverterService.convertToDTO(athleteRepository.findById(badge_number).get());
        } catch (Exception e){
            throw new NoSuchElementException();
        }
    }

    public Athlete findById(String badge_number){
        return athleteRepository.findById(badge_number).get();
    }


    public void updateAthlete(Athlete existingAthlete, Athlete athlete) {
        existingAthlete.setFirstName(athlete.getFirstName());
        existingAthlete.setLastName(athlete.getLastName());
        existingAthlete.setDateOfBirth(athlete.getDateOfBirth());
        existingAthlete.setGender(athlete.getGender());
        existingAthlete.setNationality(athlete.getNationality());
        existingAthlete.setPhoto(athlete.getPhoto());
        existingAthlete.setRole(athlete.getRole());
        existingAthlete.setCountry(athlete.getCountry());
        existingAthlete.setSports(athlete.getSports());
    }

    public void delete(String badge_number) {
        Athlete athlete = findById(badge_number);
        athleteRepository.delete(athlete);
    }
}