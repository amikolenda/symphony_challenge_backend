package is.symphony.collegeinternship.olympicgames.repositories;

import is.symphony.collegeinternship.olympicgames.models.SportCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportCountryRepository extends JpaRepository<SportCountry, Long> {
}
