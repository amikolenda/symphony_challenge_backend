package is.symphony.collegeinternship.olympicgames.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Table(name = "SPORT")
@Entity
public class Sport {

    @NotNull
    @Id
    @Column(name = "sport_name")
    private String name;
    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "athlete_sport",
            joinColumns = @JoinColumn(name = "sportId", referencedColumnName = "sport_name"),
            inverseJoinColumns = @JoinColumn(name = "athlete_id", referencedColumnName = "badge_number"))
    private Set<Athlete> athletes;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "sport")
    private Set<SportCountry> sportCountries;


    public Sport() {
    }

    public Sport(@NotNull String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Sport(@NotNull String name) {
        this.name = name;
    }
    public Sport(@NotNull String name, String description,Set<Athlete> athletes) {
        this.name = name;
        this.description = description;
        this.athletes = athletes;
    }
    public Sport(@NotNull String name, String description,Set<Athlete> athletes, Set<SportCountry> sportCountries) {
        this.name = name;
        this.description = description;
        this.athletes = athletes;
        this.sportCountries = sportCountries;
    }

    public Set<Athlete> getAthletes() {
        return athletes;
    }

    public Sport setAthletes(Set<Athlete> athletes) {
        this.athletes = athletes;
        return this;
    }

    public String getName() {
        return name;
    }

    public Sport setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Sport setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<SportCountry> getSportCountries() {
        return sportCountries;
    }

    public Sport setSportCountries(Set<SportCountry> sportCountries) {
        this.sportCountries = sportCountries;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sport sport = (Sport) o;
        return name.equals(sport.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Sport{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", athletes=" + athletes +
                ", sportCountries=" + sportCountries +
                '}';
    }
}
