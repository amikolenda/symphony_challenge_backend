package is.symphony.collegeinternship.olympicgames.repositories;

import is.symphony.collegeinternship.olympicgames.models.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, Long> {
    public Sport findSportByName(String name);
}
