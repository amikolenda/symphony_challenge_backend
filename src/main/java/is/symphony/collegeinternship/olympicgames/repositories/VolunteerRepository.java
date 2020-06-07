package is.symphony.collegeinternship.olympicgames.repositories;

import is.symphony.collegeinternship.olympicgames.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, String> {
}
