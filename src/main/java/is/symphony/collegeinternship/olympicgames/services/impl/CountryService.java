package is.symphony.collegeinternship.olympicgames.services.impl;

import is.symphony.collegeinternship.olympicgames.exceptions.NoSuchElementException;
import is.symphony.collegeinternship.olympicgames.models.Country;
import is.symphony.collegeinternship.olympicgames.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public void save(Country country) {
        countryRepository.save(country);
    }

    public void saveAll(List<Country> countries) {
        for (Country country:countries) countryRepository.save(country);
    }

    public Optional<Country> findById(String nationality) {
        try {
            return countryRepository.findById(nationality);
        } catch (Exception e){
            throw new NoSuchElementException();
        }

    }
}
