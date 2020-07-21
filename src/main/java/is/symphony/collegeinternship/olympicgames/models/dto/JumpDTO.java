package is.symphony.collegeinternship.olympicgames.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;


public class JumpDTO {
    private Long id;
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


    public JumpDTO() {
    }

    public Long getId() {
        return id;
    }

    public JumpDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public CompetitionDTO getCompetition() {
        return competition;
    }

    public JumpDTO setCompetition(CompetitionDTO competition) {
        this.competition = competition;
        return this;
    }

    public AthleteDTO getAthlete() {
        return athlete;
    }

    public JumpDTO setAthlete(AthleteDTO athlete) {
        this.athlete = athlete;
        return this;
    }

    @JsonProperty("first_jump")
    public double getFirstJump() {
        return firstJump;
    }

    public JumpDTO setFirstJump(double firstJump) {
        this.firstJump = firstJump;
        return this;
    }

    @JsonProperty("second_jump")
    public double getSecondJump() {
        return secondJump;
    }

    public JumpDTO setSecondJump(double secondJump) {
        this.secondJump = secondJump;
        return this;
    }

    @JsonProperty("third_jump")
    public double getThirdJump() {
        return thirdJump;
    }

    public JumpDTO setThirdJump(double thirdJump) {
        this.thirdJump = thirdJump;
        return this;
    }

    @Override
    public String toString() {
        return "JumpDTO{" +
                "id=" + id +
                ", competition=" + competition +
                ", athlete=" + athlete +
                ", firstJump=" + firstJump +
                ", secondJump=" + secondJump +
                ", thirdJump=" + thirdJump +
                '}';
    }
}
