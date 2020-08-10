package is.symphony.collegeinternship.olympicgames.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "VOLUNTEER")
@Entity
public class Volunteer {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username")
    @NotNull
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

    @ManyToMany(mappedBy = "volunteers",fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Set<Sport> sports;

    public Volunteer() {
    }

    public String getPassword() {
        return password;
    }

    public Volunteer setPassword(String password) {
        this.password = password;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Volunteer setId(Long id) {
        this.id = id;
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
    @JsonProperty("username")
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

    public void addSport(Sport sport){
        this.sports.add(sport);
        if (sport.getVolunteers() != null) sport.getVolunteers().add(this);
        else{
            Set<Volunteer> set = new HashSet<>();
            set.add(this);
            sport.setVolunteers(set);
        }
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
                "id=" + id +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", nationality='" + nationality + '\'' +
                ", country=" + country +
                ", photo='" + photo + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", sports=" + sports +
                '}';
    }
}
