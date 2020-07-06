package is.symphony.collegeinternship.olympicgames.models.dto;

import java.util.Objects;

public class CountryDTO {
    String countryName;

    private String countryShortCode;

    public CountryDTO() {
    }

    public String getCountryName() {
        return countryName;
    }

    public CountryDTO setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getCountryShortCode() {
        return countryShortCode;
    }

    public CountryDTO setCountryShortCode(String countryShortCode) {
        this.countryShortCode = countryShortCode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountryDTO)) return false;
        CountryDTO that = (CountryDTO) o;
        return Objects.equals(getCountryShortCode(), that.getCountryShortCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountryShortCode());
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
                "countryName='" + countryName + '\'' +
                ", countryShortCode='" + countryShortCode + '\'' +
                '}';
    }
}
