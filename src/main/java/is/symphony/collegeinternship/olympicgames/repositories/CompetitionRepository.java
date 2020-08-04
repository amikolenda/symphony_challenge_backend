package is.symphony.collegeinternship.olympicgames.repositories;

import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    public Competition findByName(String name);
    public Boolean existsByName(String name);
    public List<Competition> findAllByState(String state);
    public List<Competition> findAllByAthletes(Athlete athlete);
}
