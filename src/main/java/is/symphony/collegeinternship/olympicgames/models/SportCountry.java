package is.symphony.collegeinternship.olympicgames.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "SPORT_COUNTRY")
public class SportCountry implements Serializable {

    private static final long serialVersionUID = 6753465193828742873L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sport_id")
    @JsonIgnore
    private Sport sport;

    @ManyToOne
    @JoinColumn(name = "country_id")
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


    public SportCountry() {
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


}
