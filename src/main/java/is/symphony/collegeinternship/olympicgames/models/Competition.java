package is.symphony.collegeinternship.olympicgames.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "COMPETITION")
public class Competition implements Serializable {
    private static final long serialVersionUID = 620727377045965238L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("start_date_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateTime;

    @JsonProperty("sport")
    @ManyToOne
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    private Sport competitionSport;

    @JsonProperty("athletes")
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "competition_athlete",
            joinColumns = @JoinColumn(name = "competition_id",referencedColumnName = "id",
                    nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "athlete_id",referencedColumnName = "id",
                    nullable = false, updatable = false))
    private Set<Athlete> athletes;

    private String state;

    public Competition() {
    }

    public Long getId() {
        return id;
    }

    public Competition setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Competition setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Competition setDescription(String description) {
        this.description = description;
        return this;
    }
    @JsonProperty("start_date_time")
    public Date getDateTime() {
        return dateTime;
    }

    public Competition setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        return this;
    }
    @JsonProperty("sport")
    public Sport getCompetitionSport() {
        return competitionSport;
    }

    public Competition setCompetitionSport(Sport competitionSport) {
        this.competitionSport = competitionSport;
        return this;
    }

    public Set<Athlete> getAthletes() {
        return athletes;
    }

    public Competition setAthletes(Set<Athlete> participants) {
        this.athletes = participants;
        return this;
    }

    public String getState() {
        return state;
    }

    public Competition setState(String state) {
        this.state = state;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Competition)) return false;
        Competition that = (Competition) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", competitionSport=" + competitionSport +
                ", athletes=" + athletes +
                ", state='" + state + '\'' +
                '}';
    }
}
