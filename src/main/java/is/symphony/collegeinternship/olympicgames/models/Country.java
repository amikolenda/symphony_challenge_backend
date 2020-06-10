package is.symphony.collegeinternship.olympicgames.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable {

    @Column(name = "countryName")
    String countryName;

    @Id
    @Column(name = "countryShortCode")
    String countryShortCode;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    Set<SportCountry> sportCountries;

    public Country() {
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryShortCode() {
        return countryShortCode;
    }

    public Country setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public Country setCountryShortCode(String countryShortCode) {
        this.countryShortCode = countryShortCode;
        return this;
    }

    public Set<SportCountry> getSportCountries() {
        return sportCountries;
    }

    public Country setSportCountries(Set<SportCountry> sportCountries) {
        this.sportCountries = sportCountries;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(countryName, country.countryName) &&
                Objects.equals(countryShortCode, country.countryShortCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName, countryShortCode);
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryName='" + countryName + '\'' +
                ", countryShortCode='" + countryShortCode + '\'' +
                ", sportCountries=" + sportCountries +
                '}';
    }
}
