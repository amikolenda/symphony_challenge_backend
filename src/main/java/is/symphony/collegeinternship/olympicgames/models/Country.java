package is.symphony.collegeinternship.olympicgames.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable {

    @Column(name = "countryName")
    String countryName;

    @Id
    @Column(name = "countryShortCode")
    String countryShortCode;

    public String getCountryName() {
        return countryName;
    }

    public Country() {
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryShortCode() {
        return countryShortCode;
    }

    public void setCountryShortCode(String countryShortCode) {
        this.countryShortCode = countryShortCode;
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
                '}';
    }
}
