package is.symphony.collegeinternship.olympicgames.services.impl;
import is.symphony.collegeinternship.olympicgames.exceptions.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import is.symphony.collegeinternship.olympicgames.exceptions.ResourceNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.dto.AthleteDTO;
import is.symphony.collegeinternship.olympicgames.repositories.AthleteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;
    @Autowired
    private DTOConverterService dtoConverterService;
    @Autowired
    private CountryService countryService;


    public void save(Athlete athlete) {
            athlete.setCountry(countryService.findById(athlete.getNationality()).get());
            athleteRepository.save(athlete);
    }

    public List<AthleteDTO> findAll() throws ResourceNotFoundException {
        List<AthleteDTO> allAthletes = athleteRepository.findAll().stream().map(dtoConverterService::convertToDTO).collect(Collectors.toList());
        if (allAthletes.isEmpty()) throw new ResourceNotFoundException();
        else return allAthletes;
    }
    public AthleteDTO findById(String badge_number) throws NoSuchElementException {
        try{
            return dtoConverterService.convertToDTO(athleteRepository.findById(badge_number).get());
        } catch (Exception e){
            throw new NoSuchElementException();
        }
    }



}