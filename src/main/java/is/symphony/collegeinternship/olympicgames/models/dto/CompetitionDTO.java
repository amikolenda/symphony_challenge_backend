package is.symphony.collegeinternship.olympicgames.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class CompetitionDTO {

    private Long id;

    private String name;

    private String description;

    @JsonProperty("start_date_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateTime;

    @JsonProperty("sport")
    private SportDTO competitionSport;

    private Set<AthleteDTO> athletes;

    private String state;

    public CompetitionDTO() {
    }

    public Long getId() {
        return id;
    }

    public CompetitionDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompetitionDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CompetitionDTO setDescription(String description) {
        this.description = description;
        return this;
    }
    @JsonProperty("start_date_time")
    public Date getDateTime() {
        return dateTime;
    }

    public CompetitionDTO setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        return this;
    }
    @JsonProperty("sport")
    public SportDTO getCompetitionSport() {
        return competitionSport;
    }

    public CompetitionDTO setCompetitionSport(SportDTO competitionSport) {
        this.competitionSport = competitionSport;
        return this;
    }


    public Set<AthleteDTO> getAthletes() {
        return athletes;
    }

    public CompetitionDTO setAthletes(Set<AthleteDTO> athletes) {
        this.athletes = athletes;
        return this;
    }

    public String getState() {
        return state;
    }

    public CompetitionDTO setState(String state) {
        this.state = state;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompetitionDTO)) return false;
        CompetitionDTO that = (CompetitionDTO) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "CompetitionDTO{" +
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
