package is.symphony.collegeinternship.olympicgames.repositories;

import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.Competition;
import is.symphony.collegeinternship.olympicgames.models.Jump;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JumpRepository extends JpaRepository<Jump, Long> {
    public Jump findByCompetitionAndAthlete(Competition competition, Athlete athlete);
    public List<Jump> findAllByOrderByBestJumpDescSecondBestJumpDesc();
    public boolean existsByCompetitionAndAthlete(Competition competition, Athlete athlete);
}

