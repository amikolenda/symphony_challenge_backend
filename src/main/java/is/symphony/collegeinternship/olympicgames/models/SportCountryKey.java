package is.symphony.collegeinternship.olympicgames.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SportCountryKey implements Serializable {
    @Column(name = "sportId")
    private String sportId;
    @Column(name = "countryId")
    private String countryId;



    public SportCountryKey() {
    }

    public SportCountryKey(String sportId, String countryId) {
        this.sportId = sportId;
        this.countryId = countryId;
    }

    public String getSportId() {
        return sportId;
    }

    public SportCountryKey setSportId(String sportId) {
        this.sportId = sportId;
        return this;
    }

    public String getCountryId() {
        return countryId;
    }

    public SportCountryKey setCountryId(String countryId) {
        this.countryId = countryId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportCountryKey that = (SportCountryKey) o;
        return sportId.equals(that.sportId) &&
                countryId.equals(that.countryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportId, countryId);
    }

    @Override
    public String toString() {
        return "SportCountryKey{" +
                "sportId=" + sportId +
                ", countryId='" + countryId + '\'' +
                '}';
    }
}
