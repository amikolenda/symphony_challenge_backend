package is.symphony.collegeinternship.olympicgames.models.dto;


import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.SportCountry;
import is.symphony.collegeinternship.olympicgames.models.Volunteer;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

public class SportDTO {

    @NotNull
    private String name;
    private String description;

    @OneToMany(mappedBy = "sport")
    private Set<SportCountry> sportCountries;

    @ManyToMany(mappedBy = "athletes")
    private Set<Athlete> athletes;

    @ManyToMany(mappedBy = "volunteers")
    private Set<Volunteer> volunteers;

    public SportDTO() {
    }

    public SportDTO(@NotNull String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Set<Athlete> getAthletes() {
        return athletes;
    }

    public SportDTO setAthletes(Set<Athlete> athletes) {
        this.athletes = athletes;
        return this;
    }

    public Set<Volunteer> getVolunteers() {
        return volunteers;
    }

    public SportDTO setVolunteers(Set<Volunteer> volunteers) {
        this.volunteers = volunteers;
        return this;
    }

    public Set<SportCountry> getSportCountries() {
        return sportCountries;
    }

    public SportDTO setSportCountries(Set<SportCountry> sportCountries) {
        this.sportCountries = sportCountries;
        return this;
    }

    public String getName() {
        return name;
    }

    public SportDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SportDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportDTO sportDTO = (SportDTO) o;
        return name.equals(sportDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "SportDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sportCountries=" + sportCountries +
                ", athletes=" + athletes +
                '}';
    }
}
