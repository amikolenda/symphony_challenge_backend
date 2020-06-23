package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Volunteer;
import is.symphony.collegeinternship.olympicgames.models.dto.VolunteerDTO;
import is.symphony.collegeinternship.olympicgames.repositories.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
