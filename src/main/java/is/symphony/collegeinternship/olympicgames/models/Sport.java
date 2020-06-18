package is.symphony.collegeinternship.olympicgames.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Table(name = "SPORT")
@Entity
public class Sport implements Serializable {

    private static final long serialVersionUID = -5508644022892597453L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @Column(name = "sport_name", unique = true)
    private String name;
    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "athlete_sport",
            joinColumns = @JoinColumn(name = "sport_id"),
            inverseJoinColumns = @JoinColumn(name = "athlete_id"))
    private Set<Athlete> athletes;


    @ManyToMany
    @JoinTable(
            name = "volunteer_sport",
            joinColumns = @JoinColumn(name = "sport_id"),
            inverseJoinColumns = @JoinColumn(name = "volunteer_id"))
    private Set<Volunteer> volunteers;

    /*@OneToMany(mappedBy = "sport")
    private Set<SportCountry> sportCountries;*/


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
   /* public Sport(@NotNull String name, String description,Set<Athlete> athletes, Set<SportCountry> sportCountries) {
        this.name = name;
        this.description = description;
        this.athletes = athletes;
        this.sportCountries = sportCountries;
    }*/

    public Long getId() {
        return id;
    }

    public Sport setId(Long id) {
        this.id = id;
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

    public Set<Athlete> getAthletes() {
        return athletes;
    }

    public Sport setAthletes(Set<Athlete> athletes) {
        this.athletes = athletes;
        return this;
    }

    public Set<Volunteer> getVolunteers() {
        return volunteers;
    }

    public Sport setVolunteers(Set<Volunteer> volunteers) {
        this.volunteers = volunteers;
        return this;
    }

    /*public Set<SportCountry> getSportCountries() {
        return sportCountries;
    }

    public Sport setSportCountries(Set<SportCountry> sportCountries) {
        this.sportCountries = sportCountries;
        return this;
    }*/

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


}
