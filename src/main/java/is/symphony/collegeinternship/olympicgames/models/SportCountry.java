package is.symphony.collegeinternship.olympicgames.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "SPORT_COUNTRY")
public class SportCountry {
    @EmbeddedId
    private SportCountryKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("sportId")
    @JoinColumn(name = "sportId")
    @JsonIgnore
    private Sport sport;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("countryId")
    @JoinColumn(name = "countryId"/*, nullable = false, insertable = false, updatable = false*/)
    private Country country;

    private String name;
    private String description;

    public SportCountry(Sport sport, Country country, String name, String description) {
        this.sport = sport;
        this.country = country;
        this.name = name;
        this.description = description;
    }
    public SportCountry(Sport sport, Country country, String name) {
        this.sport = sport;
        this.country = country;
        this.name = name;

    }
    public SportCountry(Sport sport, SportCountryKey id,  Country country, String name, String description) {
        this.sport = sport;
        this.id = id;
        this.country = country;
        this.name = name;
        this.description = description;
    }

    public SportCountry() {
    }

    public SportCountryKey getId() {
        return id;
    }

    public SportCountry setId(SportCountryKey id) {
        this.id = id;
        return this;
    }

    public Sport getSport() {
        return sport;
    }

    public SportCountry setSport(Sport sport) {
        this.sport = sport;
        return this;
    }

    public Country getCountry() {
        return country;
    }

    public SportCountry setCountry(Country country) {
        this.country = country;
        return this;
    }

    public String getName() {
        return name;
    }

    public SportCountry setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SportCountry setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportCountry that = (SportCountry) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SportCountry{" +
                "id=" + id +
                ", sport=" + sport +
                ", country=" + country +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
