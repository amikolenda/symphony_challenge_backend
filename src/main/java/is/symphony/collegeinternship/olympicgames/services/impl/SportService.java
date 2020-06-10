package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementExistsException;
import is.symphony.collegeinternship.olympicgames.exceptions.NoSuchElementException;
import is.symphony.collegeinternship.olympicgames.exceptions.ResourceNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Country;
import is.symphony.collegeinternship.olympicgames.models.Sport;
import is.symphony.collegeinternship.olympicgames.models.SportCountry;
import is.symphony.collegeinternship.olympicgames.models.dto.SportDTO;
import is.symphony.collegeinternship.olympicgames.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportService {
    @Autowired
    SportRepository sportRepository;

    @Autowired
    DTOConverterService dtoConverterService;

    @Autowired
    CountryService countryService;

    public void save(Sport sport) throws ElementExistsException {
        Sport newSport = new Sport();
        newSport.setName(sport.getName());
        newSport.setDescription(sport.getDescription());
        newSport.setAthletes(sport.getAthletes());
        newSport.setSportCountries(sport.getSportCountries());

        sportRepository.save(newSport);
    }

    public List<SportDTO> findAllDTO() throws ResourceNotFoundException {
        List<SportDTO> allSports = sportRepository.findAll().stream().map(dtoConverterService::convertSportDTO).collect(Collectors.toList());
        if (allSports.isEmpty()) throw new ResourceNotFoundException();
        else return allSports;
    }
    public SportDTO findDTOByName(String name) throws NoSuchElementException {
        try{
            return dtoConverterService.convertSportDTO(sportRepository.findSportByName(name));
        } catch (Exception e){
            throw new NoSuchElementException();
        }
    }
    public Sport findByName(String name) throws NoSuchElementException{
        try{
            return sportRepository.findSportByName(name);
        } catch (Exception e){
            throw new NoSuchElementException();
        }
    }

    public void delete(String name) throws NoSuchElementException{
        Sport sport = findByName(name);
        sportRepository.delete(sport);
    }

    public void updateSport(Sport existingSport, Sport sport){
        existingSport.setName(sport.getName());
        existingSport.setDescription(sport.getDescription());
        existingSport.setAthletes(sport.getAthletes());
        existingSport.setSportCountries(sport.getSportCountries());
        existingSport.setSportCountries(sport.getSportCountries());

    }

    public void setSportCountries(Sport newSport, Sport sport){
        newSport.setSportCountries(sport.getSportCountries()
                .stream()
                .map(sportCountry -> {
                    Country country = countryService.findById(sportCountry.getCountry().getCountryShortCode()).get();
                    SportCountry newSportCountry = new SportCountry();
                    newSportCountry.setCountry(country);
                    newSportCountry.setSport(newSport);
                    newSportCountry.setName(sportCountry.getName());
                    newSportCountry.setDescription(sportCountry.getDescription());
                    return newSportCountry;
                }).collect(Collectors.toSet()));

    }



}
