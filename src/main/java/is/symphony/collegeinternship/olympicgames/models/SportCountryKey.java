package is.symphony.collegeinternship.olympicgames.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SportCountryKey implements Serializable {
    private String sportId;
    String countryShortCode;



    public SportCountryKey() {
    }

    public SportCountryKey(String sportId, String countryShortCode) {
        this.sportId = sportId;
        this.countryShortCode = countryShortCode;
    }

    public String getSportId() {
        return sportId;
    }

    public SportCountryKey setSportId(String sportId) {
        this.sportId = sportId;
        return this;
    }

    public String getCountryShortCode() {
        return countryShortCode;
    }

    public SportCountryKey setCountryShortCode(String countryShortCode) {
        this.countryShortCode = countryShortCode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportCountryKey)) return false;
        SportCountryKey that = (SportCountryKey) o;
        return Objects.equals(getSportId(), that.getSportId()) &&
                Objects.equals(getCountryShortCode(), that.getCountryShortCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSportId(), getCountryShortCode());
    }

    @Override
    public String toString() {
        return "SportCountryKey{" +
                "sportId=" + sportId +
                ", countryShortCode='" + countryShortCode + '\'' +
                '}';
    }
}
