package is.symphony.collegeinternship.olympicgames.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable {

    private static final long serialVersionUID = 1121259828541782353L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @Column(name = "countryName")
    String countryName;

    @Column(name = "countryShortCode")
    private String countryShortCode;

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

    public Long getId() {
        return id;
    }

    public Country setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", countryShortCode='" + countryShortCode + '\'' +
                '}';
    }
}
