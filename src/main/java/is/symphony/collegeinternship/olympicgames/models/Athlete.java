package is.symphony.collegeinternship.olympicgames.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "ATHLETE")
@Entity
public class Athlete implements Serializable {
    private static final long serialVersionUID = -6794424089409161547L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

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

    @Column(name = "badge_number")
    @NotNull
    private String badgeNumber;
    @Column(name = "photo")
    private String photo;
    @Column(name = "gender")
    private String gender;
    @Column(name = "role")
    private String role = "ATHLETE";

    @ManyToOne
    private Sport sport;

    public Athlete() {
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("date_of_birth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public Country getCountry() {
        return country;
    }

    @JsonProperty("badge_number")
    public String getBadgeNumber() {
        return badgeNumber;
    }


    public String getPhoto() {
        return photo;
    }


    public String getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public Athlete setId(Long id) {
        this.id = id;
        return this;
    }

    public Sport getSport() {
        return sport;
    }

    public Athlete setSport(Sport sport) {
        this.sport = sport;
        return this;
    }

    public Athlete setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Athlete setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Athlete setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public Athlete setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public Athlete setCountry(Country country) {
        this.country = country;
        return this;
    }

    public Athlete setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
        return this;
    }

    public Athlete setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Athlete setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Athlete setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return Objects.equals(badgeNumber, athlete.badgeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(badgeNumber);
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", nationality='" + nationality + '\'' +
                ", country=" + country +
                ", badgeNumber='" + badgeNumber + '\'' +
                ", photo='" + photo + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", sport=" + sport +
                '}';
    }
}
