package is.symphony.collegeinternship.olympicgames.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "JUMP")
public class Jump implements Serializable {
    private static final long serialVersionUID = 722536052588391358L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("competition")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    @NotNull
    private Competition competition;

    @JsonProperty("athlete")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "athlete_id", referencedColumnName = "id")
    private Athlete athlete;

    @JsonProperty("first_jump")
    private double firstJump;

    @JsonProperty("second_jump")
    private double secondJump;

    @JsonProperty("third_jump")
    private double thirdJump;

    private double bestJump;

    private double secondBestJump;

    public Jump() {
    }

    public Long getId() {
        return id;
    }

    public Jump setId(Long id) {
        this.id = id;
        return this;
    }

    public Competition getCompetition() {
        return competition;
    }

    public Jump setCompetition(Competition competition) {
        this.competition = competition;
        return this;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public Jump setAthlete(Athlete athlete) {
        this.athlete = athlete;
        return this;
    }

    @JsonProperty("first_jump")
    public double getFirstJump() {
        return firstJump;
    }

    public Jump setFirstJump(double firstJump) {
        this.firstJump = firstJump;
        return this;
    }

    @JsonProperty("second_jump")
    public double getSecondJump() {
        return secondJump;
    }

    public Jump setSecondJump(double secondJump) {
        this.secondJump = secondJump;
        return this;
    }

    @JsonProperty("third_jump")
    public double getThirdJump() {
        return thirdJump;
    }

    public Jump setThirdJump(double thirdJump) {
        this.thirdJump = thirdJump;
        return this;
    }

    public double getBestJump() {
        return bestJump;
    }

    public Jump setBestJump(double bestJump) {
        this.bestJump = bestJump;
        return this;
    }

    public double getSecondBestJump() {
        return secondBestJump;
    }

    public Jump setSecondBestJump(double secondBestJump) {
        this.secondBestJump = secondBestJump;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jump)) return false;
        Jump jump = (Jump) o;
        return Objects.equals(getId(), jump.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Jump{" +
                "id=" + id +
                ", competition=" + competition +
                ", athlete=" + athlete +
                ", firstJump=" + firstJump +
                ", secondJump=" + secondJump +
                ", thirdJump=" + thirdJump +
                ", bestJump=" + bestJump +
                ", secondBestJump=" + secondBestJump +
                '}';
    }
}
