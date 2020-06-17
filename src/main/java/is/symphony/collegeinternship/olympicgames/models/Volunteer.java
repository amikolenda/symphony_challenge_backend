package is.symphony.collegeinternship.olympicgames.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Table(name = "VOLUNTEER")
@Entity
public class Volunteer {

    @Column(name = "user_name")
    @NotNull
    @Id
    private String userName;

    @Column(name = "password")
    @NotNull
   // @Size(min = 59, max = 61)
    private String password;

    @Column(name = "first_name")
    @NotNull
    @Size(min = 2)
    private String firstName;
    @Column(name = "last_name")
    @NotNull
    @Size(min = 2)
    private String lastName;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "nationality")
    private String nationality;

    @ManyToOne
    @JoinColumn(name = "countryName", referencedColumnName = "countryName")
    private Country country;


    @Column(name = "photo")
    private String photo;
    @Column(name = "gender")
    private String gender;
    @Column(name = "role")
    private String role = "VOLUNTEER";


    @ManyToMany(mappedBy = "volunteers")
    @JsonIgnore
    private Set<Sport> sports;

    public Volunteer() {
    }
    public Volunteer(String firstName, String lastName, String userName, String password, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.nationality = nationality;
    }

    public String getPassword() {
        return password;
    }

    public Volunteer setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<Sport> getSports() {
        return sports;
    }

    public Volunteer setSports(Set<Sport> sports) {
        this.sports = sports;
        return this;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    public Volunteer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public Volunteer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    @JsonProperty("date_of_birth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Volunteer setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getNationality() {
        return nationality;
    }

    public Volunteer setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public Country getCountry() {
        return country;
    }

    public Volunteer setCountry(Country country) {
        this.country = country;
        return this;
    }
    @JsonProperty("user_name")
    public String getUserName() {
        return userName;
    }

    public Volunteer setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public Volunteer setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Volunteer setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Volunteer setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(userName, volunteer.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
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
