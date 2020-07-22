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
@Table(name = "RESULT")
public class Result implements Serializable {
    private static final long serialVersionUID = 79822689328238330L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    private Integer position;

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
    @JsonProperty("best_jump")
    private double bestJump;

    private double secondBestJump;

    private String medal;

    public Result() {
    }

    public Long getId() {
        return id;
    }

    public Result setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPosition() {
        return position;
    }

    public Result setPosition(Integer position) {
        this.position = position;
        return this;
    }

    public Competition getCompetition() {
        return competition;
    }

    public Result setCompetition(Competition competition) {
        this.competition = competition;
        return this;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public Result setAthlete(Athlete athlete) {
        this.athlete = athlete;
        return this;
    }
    @JsonProperty("first_jump")
    public double getFirstJump() {
        return firstJump;
    }

    public Result setFirstJump(double firstJump) {
        this.firstJump = firstJump;
        return this;
    }
    @JsonProperty("second_jump")
    public double getSecondJump() {
        return secondJump;
    }

    public Result setSecondJump(double secondJump) {
        this.secondJump = secondJump;
        return this;
    }
    @JsonProperty("third_jump")
    public double getThirdJump() {
        return thirdJump;
    }

    public Result setThirdJump(double thirdJump) {
        this.thirdJump = thirdJump;
        return this;
    }
    @JsonProperty("best_jump")
    public double getBestJump() {
        return bestJump;
    }

    public Result setBestJump(double bestJump) {
        this.bestJump = bestJump;
        return this;
    }

    public double getSecondBestJump() {
        return secondBestJump;
    }

    public Result setSecondBestJump(double secondBestJump) {
        this.secondBestJump = secondBestJump;
        return this;
    }

    public String getMedal() {
        return medal;
    }

    public Result setMedal(String medal) {
        this.medal = medal;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;
        Result result = (Result) o;
        return Objects.equals(getId(), result.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", position=" + position +
                ", competition=" + competition +
                ", athlete=" + athlete +
                ", firstJump=" + firstJump +
                ", secondJump=" + secondJump +
                ", thirdJump=" + thirdJump +
                ", bestJump=" + bestJump +
                ", secondBestJump=" + secondBestJump +
                ", medal='" + medal + '\'' +
                '}';
    }
}
