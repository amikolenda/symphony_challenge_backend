package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Sport;
import is.symphony.collegeinternship.olympicgames.models.Volunteer;
import is.symphony.collegeinternship.olympicgames.models.dto.VolunteerDTO;
import is.symphony.collegeinternship.olympicgames.repositories.SportRepository;
import is.symphony.collegeinternship.olympicgames.repositories.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VolunteerService {
    private static Logger LOGGER = LoggerFactory.getLogger(VolunteerService.class);

    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private DTOConverterService dtoConverterService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SportRepository sportRepository;


    public VolunteerDTO save(VolunteerDTO volunteerDTO) throws ElementExistsException {
        if (volunteerRepository.existsByUserName(volunteerDTO.getUserName())) {
            LOGGER.error("Volunteer already exists!");
            throw new ElementExistsException();
        }
        LOGGER.info("Saving volunteer...");
        Volunteer volunteer = dtoConverterService.convertVolunteerDTOToDAO(volunteerDTO);
        volunteer.setCountry(countryService.findByCountryShortCode(volunteer.getNationality()));
        String password = passwordEncoder.encode(volunteer.getPassword());
        volunteer.setPassword(password);
        volunteerRepository.save(volunteer);

        Set<Sport> sportsSet = volunteer.getSports();
        if (sportsSet != null) setSports(volunteer, sportsSet);

        volunteerRepository.save(volunteer);
        LOGGER.info("Saved {}", volunteer);
        return dtoConverterService.convertVolunteerDTO(volunteer);
    }

    public Volunteer save(Volunteer volunteer){
        if (volunteerRepository.existsByUserName(volunteer.getUserName())) {
            Volunteer existingVolunteer = volunteerRepository.findVolunteerByUserName(volunteer.getUserName());
            volunteer.setId(existingVolunteer.getId());
        }
        LOGGER.info("Saving/updating volunteer...");
        volunteer.setCountry(countryService.findByCountryShortCode(volunteer.getNationality()));
        String password = passwordEncoder.encode(volunteer.getPassword());
        volunteer.setPassword(password);
        volunteerRepository.save(volunteer);

        Set<Sport> sportsSet = volunteer.getSports();
        if (sportsSet != null) setSports(volunteer, sportsSet);

        volunteerRepository.save(volunteer);
        LOGGER.info("Saved/updated {}", volunteer);
        return volunteer;
    }

    public VolunteerDTO findDTOByUserName(String userName) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get a volunteer...");
            Volunteer found = volunteerRepository.findVolunteerByUserName(userName);
            LOGGER.info("Found {}", found);
            return dtoConverterService.convertVolunteerDTO(found);
        } catch (Exception e){
            LOGGER.error("Volunteer not found.");
            throw new ElementNotFoundException();
        }
    }

    public Volunteer findByUserName(String userName) throws ElementNotFoundException {
        try{
            LOGGER.info("Accessing DB to get a volunteer...");
            Volunteer found = volunteerRepository.findVolunteerByUserName(userName);
            LOGGER.info("Found {}", found);
            return found;
        } catch (Exception e){
            LOGGER.error("Volunteer not found.");
            throw new ElementNotFoundException();
        }
    }
    public List<VolunteerDTO> findAllDTO() throws ElementNotFoundException{
        LOGGER.info("Accessing DB to get all volunteers...");
        List<Volunteer> allVolunteersList = volunteerRepository.findAll();
        LOGGER.info("Found {} volunteers!", allVolunteersList.size());
        List<VolunteerDTO> allVolunteers = allVolunteersList.stream().map(dtoConverterService::convertVolunteerDTO).collect(Collectors.toList());
        if (allVolunteers.isEmpty()) {
            LOGGER.error("Could not find any volunteers!");
            throw new ElementNotFoundException();
        }
        else return allVolunteers;
    }

    public VolunteerDTO updateVolunteer(VolunteerDTO volunteerDTO, String userName) throws ElementNotFoundException{
        if (!volunteerRepository.existsByUserName(userName)) {
            LOGGER.error("Volunteer does not exist!");
            throw new ElementNotFoundException();
        }
        LOGGER.info("Updating volunteer...");
        Volunteer existingVolunteer = volunteerRepository.findVolunteerByUserName(userName);
        Volunteer volunteer = dtoConverterService.convertVolunteerDTOToDAO(volunteerDTO);
        volunteer.setId(existingVolunteer.getId());
        String password = passwordEncoder.encode(volunteer.getPassword());
        volunteer.setPassword(password);
        volunteer.setCountry(countryService.findByCountryShortCode(volunteer.getNationality()));

        Set<Sport> sportsSet = volunteer.getSports();
        if (sportsSet != null) setSports(volunteer, sportsSet);

        volunteerRepository.save(volunteer);
        LOGGER.info("Updated {}", volunteer);
        return dtoConverterService.convertVolunteerDTO(volunteer);
    }

    public void delete(String userName) throws ElementNotFoundException{
        LOGGER.info("Accessing DB to get a volunteer...");
        Volunteer volunteer = findByUserName(userName);
        LOGGER.info("Deleting volunteer...");
        volunteerRepository.delete(findByUserName(userName));
        LOGGER.info("Volunteer deleted!");
    }

    public Set<Volunteer> setVolunteers(Set<Volunteer> volunteersSet) {
        LOGGER.info("Setting volunteers to sport...");
        Set<Volunteer> temp = new HashSet<>();
        for (Volunteer volunteer : volunteersSet) {
            String name = volunteer.getUserName();
            if (volunteerRepository.existsByUserName(name)) {
                volunteer.setId(findByUserName(name).getId());
                volunteer.setCountry(countryService.findByCountryShortCode(volunteer.getNationality()));
                temp.add(volunteer);
            } else {
                LOGGER.info("Volunteer does not exist!");
            }
        }
        LOGGER.info("Volunteer set.");
        return temp;
    }
    public Set<Sport> setSports(Volunteer volunteer, Set<Sport> sportsSet){
        LOGGER.info("Setting sports to volunteer...");
        Set<Sport> temp = new HashSet<>();
        for (Sport sport : sportsSet) {
            String name = sport.getName();
            if (sportRepository.existsByName(name)) {
                sport.setId(sportRepository.findSportByName(name).getId());
                volunteer.addSport(sport);
                temp.add(sport);
            } else {
                LOGGER.info("Sport does not exist!");
            }
        }
        LOGGER.info("Sports set.");
        return temp;
    }
}
