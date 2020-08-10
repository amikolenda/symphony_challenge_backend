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

import javax.persistence.PreRemove;
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

    public AthleteDTO findDTOById(Long id) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get an athlete...");
            Athlete found = athleteRepository.findById(id).get();
            LOGGER.info("Found {}", found);
            return dtoConverterService.convertToDTO(found);
        } catch (Exception e){
            LOGGER.error("Athlete not found.");
            throw new ElementNotFoundException();
        }
    }

    public AthleteDTO findDTOByBadgeNumber(String badgeNumber) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get an athlete...");
            Athlete found = athleteRepository.findByBadgeNumber(badgeNumber);
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

    private Athlete findById(Long id) {
        try{
            LOGGER.info("Accessing DB to get an athlete...");
            Athlete found = athleteRepository.findById(id).get();
            LOGGER.info("Athlete found.");
            return found;
        } catch (Exception e){
            LOGGER.info("Athlete not found.");
            throw new ElementNotFoundException();
        }
    }

    public AthleteDTO updateAthlete(AthleteDTO athleteDTO) throws ElementNotFoundException{
        if (!athleteRepository.existsById(athleteDTO.getId())) {
            LOGGER.error("Athlete does not exist!");
            throw new ElementNotFoundException();
        }
        LOGGER.info("Updating an athlete...");
        Athlete newAthlete = dtoConverterService.convertAthleteDTOToDAO(athleteDTO);
        newAthlete.setCountry(countryService.findByCountryShortCode(newAthlete.getNationality()));

        Set<Sport> oldSportsSet = findById(athleteDTO.getId()).getSports();
        removeAthlete(newAthlete, oldSportsSet);
        Set<Sport> sportsSet = newAthlete.getSports();
        if (sportsSet != null) newAthlete.setSports(setSports(newAthlete,sportsSet));

        athleteRepository.save(newAthlete);
        LOGGER.info("Updated {}", newAthlete);
        return dtoConverterService.convertToDTO(newAthlete);
    }

    public void delete(Long id) throws ElementNotFoundException{
        LOGGER.info("Accessing DB to get an athlete...");
        Athlete athlete = findById(id);
        LOGGER.info("Deleting an athlete...");
        athleteRepository.delete(athlete);
        LOGGER.info("Athlete deleted.");
    }

    public Set<Athlete> setAthletes(Set<Athlete> athletesSet){
        LOGGER.info("Setting athletes to sport/competition...");
        Set<Athlete> temp = new HashSet<>();
        for (Athlete athlete : athletesSet) {
            Long id = athlete.getId();
            if (athleteRepository.existsById(id)) {
                Athlete existingAthlete = athleteRepository.findById(id).get();
                temp.add(existingAthlete);
            } else {
                LOGGER.info("Athlete does not exist!");
            }
        }
        LOGGER.info("Athletes set.");
        return temp;
    }

    public Athlete setAthlete(Athlete athlete) throws ElementNotFoundException{
        LOGGER.info("Checking if Athlete exists...");
        if (athleteRepository.existsById(athlete.getId())){
            Long id = athlete.getId();
            Athlete existingAthlete = findById(id);
            LOGGER.info("Athlete exists");
            return existingAthlete;
        } else {
            LOGGER.error("Athlete does not exist!");
            throw new ElementNotFoundException();
        }
    }


    private Set<Sport> setSports(Athlete athlete,Set<Sport> sportsSet){
        LOGGER.info("Setting sports to athlete...");
        Set<Sport> temp = new HashSet<>();
        for (Sport sport : sportsSet) {
            Long id = sport.getId();
            if (sportRepository.existsById(id)) {
                Sport existingSport = sportRepository.findById(id).get();
                athlete.addSport(existingSport);
                temp.add(existingSport);
            } else {
                LOGGER.info("Sport does not exist!");
            }
        }
        LOGGER.info("Sports set.");
        return temp;
    }
    @PreRemove
    private void removeAthlete(Athlete athlete,Set<Sport> sportsSet){
        LOGGER.info("Remove athlete from sport...");
        for (Sport sport : sportsSet) {
            Long id = sport.getId();
            if (sportRepository.existsById(id)) {
                Sport existingSport = sportRepository.findById(id).get();
                existingSport.getAthletes().remove(athlete);
            } else {
                LOGGER.info("Sport does not exist!");
            }
        }
        LOGGER.info("Athletes removed.");
    }
}