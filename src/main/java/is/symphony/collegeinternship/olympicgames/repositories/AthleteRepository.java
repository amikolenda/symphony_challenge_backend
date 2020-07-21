package is.symphony.collegeinternship.olympicgames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import is.symphony.collegeinternship.olympicgames.models.Athlete;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    public Athlete findByBadgeNumber(String badgeNumber);
    public Boolean existsByBadgeNumber(String badgeNumber);
}
