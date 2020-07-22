package is.symphony.collegeinternship.olympicgames.repositories;

import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.Competition;
import is.symphony.collegeinternship.olympicgames.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    public Result findByCompetitionAndAthlete(Competition competition, Athlete athlete);
    public List<Result> findAllByCompetitionOrderByBestJumpDescSecondBestJumpDesc(Competition competition);
    public boolean existsByCompetitionAndAthlete(Competition competition, Athlete athlete);
}
