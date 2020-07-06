package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Sport;
import is.symphony.collegeinternship.olympicgames.repositories.SportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.dto.AthleteDTO;
import is.symphony.collegeinternship.olympicgames.repositories.AthleteRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    @Autowired
    private SportRepository sportRepository;


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

    public Athlete save(Athlete athlete) throws ElementNotFoundException{
        if (athleteRepository.existsByBadgeNumber(athlete.getBadgeNumber())) {
            Athlete existingAthlete = athleteRepository.findByBadgeNumber(athlete.getBadgeNumber());
            athlete.setId(existingAthlete.getId());
        }
        LOGGER.info("Saving/updating athlete...");
        athlete.setCountry(countryService.findByCountryShortCode(athlete.getNationality()));
        Set<Sport> sportsSet = athlete.getSports();
        Athlete save = athleteRepository.save(athlete);
        LOGGER.info("Saved/updated {}", save);
        return save;
    }

    public List<AthleteDTO> findAllDTO() {
        LOGGER.info("Accessing DB to get all athletes...");

        List<Athlete> allAthletes = athleteRepository.findAll();
        LOGGER.info("Found {} athletes!", allAthletes.size());
        List<AthleteDTO> allAthletesList = allAthletes.stream().map(dtoConverterService::convertToDTO).collect(Collectors.toList());
        return allAthletesList;
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

        Set<Sport> sportsSet = newAthlete.getSports();
        if (sportsSet != null) setSports(newAthlete, sportsSet);

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

    public Set<Athlete> setAthletes(Set<Athlete> athletesSet){
        LOGGER.info("Setting athletes to sport...");
        Set<Athlete> temp = new HashSet<>();
        for (Athlete athlete : athletesSet) {
            String badgeNumber = athlete.getBadgeNumber();
            if (athleteRepository.existsByBadgeNumber(badgeNumber)) {
                athlete.setId(findByBadgeNumber(badgeNumber).getId());
                athlete.setCountry(countryService.findByCountryShortCode(athlete.getNationality()));
                temp.add(athlete);
            } else {
                LOGGER.info("Athlete does not exist!");
            }
        }
        LOGGER.info("Athletes set.");
        return temp;
    }

    private Set<Sport> setSports(Athlete athlete, Set<Sport> sportsSet){
        LOGGER.info("Setting sports to athlete...");
        Set<Sport> temp = new HashSet<>();
        for (Sport sport : sportsSet) {
            String name = sport.getName();
            if (sportRepository.existsByName(name)) {
                sport.setId(sportRepository.findSportByName(name).getId());
                athlete.addSport(sport);
                temp.add(sport);
            } else {
                LOGGER.info("Sport does not exist!");
            }
        }
        LOGGER.info("Sports set.");
        return temp;
    }
}