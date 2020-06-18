package is.symphony.collegeinternship.olympicgames.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import is.symphony.collegeinternship.olympicgames.models.Country;
import is.symphony.collegeinternship.olympicgames.models.Sport;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

public class AthleteDTO {

    @NotNull
    @Size(min = 2)
    @JsonProperty("first_name")
    private String firstName;

    @NotNull
    @Size(min = 2)
    @JsonProperty("last_name")
    private String lastName;

    @NotNull
    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("country")
    private Country country;

    @NotNull
    @Id
    @JsonProperty("badge_number")
    private String badgeNumber;

    @JsonProperty("photo")
    private String photo;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("role")
    private String role = "ATHLETE";

    @JsonIgnore
    private Set<Sport> sports;


    public AthleteDTO() {
    }

    public AthleteDTO(@NotNull @Size(min = 2) String firstName, @NotNull @Size(min = 2) String lastName, @NotNull String dateOfBirth, String nationality, Country country, @NotNull String badgeNumber, String photo, String gender, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.country = country;
        this.badgeNumber = badgeNumber;
        this.photo = photo;
        this.gender = gender;
        this.role = role;
    }


    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public String getGender() {
        return gender;
    }

    public Country getCountry() {
        return country;
    }

    public Set<Sport> getSports() {
        return sports;
    }

    public AthleteDTO setSports(Set<Sport> sports) {
        this.sports = sports;
        return this;
    }

    public AthleteDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AthleteDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AthleteDTO setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public AthleteDTO setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public AthleteDTO setCountry(Country country) {
        this.country = country;
        return this;
    }

    public AthleteDTO setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
        return this;
    }

    public AthleteDTO setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public AthleteDTO setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public AthleteDTO setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AthleteDTO athlete = (AthleteDTO) o;
        return Objects.equals(badgeNumber, athlete.badgeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(badgeNumber);
    }

    @Override
    public String toString() {
        return "AthleteDTO{" +
                "first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", date_of_birth='" + dateOfBirth + '\'' +
                ", nationality='" + nationality + '\'' +
                ", country=" + country +
                ", badge_number='" + badgeNumber + '\'' +
                ", photo='" + photo + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}


