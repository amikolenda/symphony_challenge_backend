package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.NoSuchElementException;
import is.symphony.collegeinternship.olympicgames.exceptions.ResourceNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Volunteer;
import is.symphony.collegeinternship.olympicgames.models.dto.VolunteerDTO;
import is.symphony.collegeinternship.olympicgames.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private DTOConverterService dtoConverterService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void save(Volunteer volunteer) throws ElementExistsException {
        volunteer.setCountry(countryService.findByCountryShortCode(volunteer.getNationality()));
        String password = passwordEncoder.encode(volunteer.getPassword());
        volunteer.setPassword(password);
        volunteerRepository.save(volunteer);
    }
    public VolunteerDTO findDTOById(String userName) throws NoSuchElementException {
        try{
            return dtoConverterService.convertVolunteerDTO(volunteerRepository.findById(userName).get());
        } catch (Exception e){
            throw new NoSuchElementException();
        }
    }

    public Volunteer findById(String userName) throws NoSuchElementException {
        try{
            return volunteerRepository.findById(userName).get();
        } catch (Exception e){
            throw new NoSuchElementException();
        }
    }
    public List<VolunteerDTO> findAllDTO() throws ResourceNotFoundException{
        List<VolunteerDTO> allVolunteers = volunteerRepository.findAll().stream().map(dtoConverterService::convertVolunteerDTO).collect(Collectors.toList());
        if (allVolunteers.isEmpty()) throw new ResourceNotFoundException();
        else return allVolunteers;
    }

    public void updateVolunteer(Volunteer existingVolunteer, Volunteer volunteer) {
        existingVolunteer.setUserName(volunteer.getUserName());
        String password = passwordEncoder.encode(volunteer.getPassword());
        existingVolunteer.setPassword(password);
        existingVolunteer.setFirstName(volunteer.getFirstName());
        existingVolunteer.setLastName(volunteer.getLastName());
        existingVolunteer.setDateOfBirth(volunteer.getDateOfBirth());
        existingVolunteer.setGender(volunteer.getGender());
        existingVolunteer.setNationality(volunteer.getNationality());
        existingVolunteer.setPhoto(volunteer.getPhoto());
        existingVolunteer.setRole(volunteer.getRole());
        existingVolunteer.setCountry(countryService.findByCountryShortCode(volunteer.getNationality()));
        existingVolunteer.setSports(volunteer.getSports());
        volunteerRepository.save(existingVolunteer);
    }

    public void delete(String userName) {
        volunteerRepository.delete(findById(userName));
    }
}
