package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.ElementNotFoundException;
import is.symphony.collegeinternship.olympicgames.models.Country;
import is.symphony.collegeinternship.olympicgames.models.dto.CountryDTO;
import is.symphony.collegeinternship.olympicgames.repositories.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private static Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    DTOConverterService dtoConverterService;

    public void save(Country country) {

        Country save = countryRepository.save(country);
        LOGGER.info("Saved {}", save);
    }

    public List<CountryDTO> findAllDTO(){
        LOGGER.info("Accessing DB to get all countries...");

        List<Country> allCountries = countryRepository.findAll();
        LOGGER.info("Found {} countries!", allCountries.size());
        List<CountryDTO> allCountriesList = allCountries.stream().map(dtoConverterService::convertCountryToDTO).collect(Collectors.toList());
        return allCountriesList;
    }

    public void saveAll(List<Country> countries) {
        for (Country country:countries) countryRepository.save(country);
    }

    public Optional<Country> findById(Long id) {
        try {
            LOGGER.info("Accessing DB to get a country...");
            Optional<Country> found = countryRepository.findById(id);
            LOGGER.info("Country found...");
            return found;
        } catch (Exception e){
            LOGGER.error("Country not found...");
            throw new ElementNotFoundException();
        }
    }

    public Country findByCountryShortCode(String countryShortCode) {
        try {
            LOGGER.info("Accessing DB to get a country...");
            Country found = countryRepository.findByCountryShortCode(countryShortCode);
            LOGGER.info("Country found...");
            return found;
        } catch (Exception e){
            LOGGER.error("Country not found...");
            throw new ElementNotFoundException();
        }
    }

}
