package is.symphony.collegeinternship.olympicgames.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import is.symphony.collegeinternship.olympicgames.models.Country;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

public class VolunteerDTO {
    @JsonProperty("first_name")
    @NotNull
    @Size(min = 2)
    private String firstName;
    @JsonProperty("last_name")
    @NotNull
    @Size(min = 2)
    private String lastName;
    @JsonProperty("date_of_birth")
    @NotNull
    private String dateOfBirth;
    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("country")
    private Country country;

    @JsonProperty("user_name")
    @NotNull
    private String userName;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("role")
    private String role = "VOLUNTEER";
    @JsonProperty("password")
    private String password;
    @JsonProperty("sports")
    private Set<SportDTO> sports;

    public VolunteerDTO() {
    }
    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    public VolunteerDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public VolunteerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    @JsonProperty("date_of_birth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public VolunteerDTO setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public VolunteerDTO setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public Country getCountry() {
        return country;
    }

    public VolunteerDTO setCountry(Country country) {
        this.country = country;
        return this;
    }
    @JsonProperty("user_name")
    public String getUserName() {
        return userName;
    }

    public VolunteerDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public VolunteerDTO setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public VolunteerDTO setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getRole() {
        return role;
    }

    public VolunteerDTO setRole(String role) {
        this.role = role;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public VolunteerDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<SportDTO> getSports() {
        return sports;
    }

    public VolunteerDTO setSports(Set<SportDTO> sports) {
        this.sports = sports;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolunteerDTO that = (VolunteerDTO) o;
        return Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "VolunteerDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", nationality='" + nationality + '\'' +
                ", country=" + country +
                ", userName='" + userName + '\'' +
                ", photo='" + photo + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", sports=" + sports +
                '}';
    }
}
