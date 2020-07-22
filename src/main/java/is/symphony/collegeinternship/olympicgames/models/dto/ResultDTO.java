package is.symphony.collegeinternship.olympicgames.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.Competition;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ResultDTO {
    private Long id;

    private Integer position;
    @NotNull
    private CompetitionDTO competition;

    @NotNull
    private AthleteDTO athlete;

    @JsonProperty("first_jump")
    private double firstJump;

    @JsonProperty("second_jump")
    private double secondJump;

    @JsonProperty("third_jump")
    private double thirdJump;
    @JsonProperty("best_jump")
    private double bestJump;

    private String medal;

    public ResultDTO() {
    }

    public Long getId() {
        return id;
    }

    public ResultDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPosition() {
        return position;
    }

    public ResultDTO setPosition(Integer position) {
        this.position = position;
        return this;
    }

    public CompetitionDTO getCompetition() {
        return competition;
    }

    public ResultDTO setCompetition(CompetitionDTO competition) {
        this.competition = competition;
        return this;
    }

    public AthleteDTO getAthlete() {
        return athlete;
    }

    public ResultDTO setAthlete(AthleteDTO athlete) {
        this.athlete = athlete;
        return this;
    }

    @JsonProperty("first_jump")
    public double getFirstJump() {
        return firstJump;
    }

    public ResultDTO setFirstJump(double firstJump) {
        this.firstJump = firstJump;
        return this;
    }
    @JsonProperty("second_jump")
    public double getSecondJump() {
        return secondJump;
    }

    public ResultDTO setSecondJump(double secondJump) {
        this.secondJump = secondJump;
        return this;
    }
    @JsonProperty("third_jump")
    public double getThirdJump() {
        return thirdJump;
    }

    public ResultDTO setThirdJump(double thirdJump) {
        this.thirdJump = thirdJump;
        return this;
    }
    @JsonProperty("best_jump")
    public double getBestJump() {
        return bestJump;
    }

    public ResultDTO setBestJump(double bestJump) {
        this.bestJump = bestJump;
        return this;
    }

    public String getMedal() {
        return medal;
    }

    public ResultDTO setMedal(String medal) {
        this.medal = medal;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultDTO)) return false;
        ResultDTO resultDTO = (ResultDTO) o;
        return Objects.equals(getId(), resultDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "id=" + id +
                ", position=" + position +
                ", competition=" + competition +
                ", athlete=" + athlete +
                ", firstJump=" + firstJump +
                ", secondJump=" + secondJump +
                ", thirdJump=" + thirdJump +
                ", bestJump=" + bestJump +
                ", medal='" + medal + '\'' +
                '}';
    }
}
