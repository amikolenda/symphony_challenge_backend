package is.symphony.collegeinternship.olympicgames.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import is.symphony.collegeinternship.olympicgames.models.Country;
import is.symphony.collegeinternship.olympicgames.models.Sport;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

public class VolunteerDTO {
    @Column(name = "first_name")
    @NotNull
    @Size(min = 2)
    private String firstName;
    @Column(name = "last_name")
    @NotNull
    @Size(min = 2)
    private String lastName;
    @Column(name = "date_of_birth")
    @NotNull
    private String dateOfBirth;
    @Column(name = "nationality")
    private String nationality;

    @ManyToOne
    @JoinColumn(name = "countryName", referencedColumnName = "countryName")
    private Country country;

    @Column(name = "user_name")
    @NotNull
    @Id
    private String userName;
    @Column(name = "photo")
    private String photo;
    @Column(name = "gender")
    private String gender;
    @Column(name = "role")
    private String role = "VOLUNTEER";
    @Column(name = "password")
    private String password;


    public VolunteerDTO() {
    }
    public VolunteerDTO(String firstName, String lastName, String userName, String password, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.nationality = nationality;
    }

    public String getFirstName() {
        return firstName;
    }

    public VolunteerDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public VolunteerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

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
                '}';
    }
}
