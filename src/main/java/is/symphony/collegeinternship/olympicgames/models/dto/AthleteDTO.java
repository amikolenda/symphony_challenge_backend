package is.symphony.collegeinternship.olympicgames.models.dto;

import is.symphony.collegeinternship.olympicgames.models.Country;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class AthleteDTO {

    @NotNull
    @Size(min = 2)
    private String first_name;

    @NotNull
    @Size(min = 2)
    private String last_name;

    @NotNull
    private String date_of_birth;

    private String nationality;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "countryName", referencedColumnName = "countryName")
    private Country country;

    @NotNull
    @Id
    private String badge_number;

    private String photo;
    private String gender;
    private String role = "ATHLETE";

    public AthleteDTO() {
    }

    public AthleteDTO(@NotNull @Size(min = 2) String first_name, @NotNull @Size(min = 2) String last_name, @NotNull String date_of_birth, String nationality, Country country, @NotNull String badge_number, String photo, String gender, String role) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.nationality = nationality;
        this.country = country;
        this.badge_number = badge_number;
        this.photo = photo;
        this.gender = gender;
        this.role = role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBadge_number() {
        return badge_number;
    }

    public void setBadge_number(String badge_number) {
        this.badge_number = badge_number;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AthleteDTO athlete = (AthleteDTO) o;
        return Objects.equals(badge_number, athlete.badge_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(badge_number);
    }

    @Override
    public String toString() {
        return "AthleteDTO{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", nationality='" + nationality + '\'' +
                ", country=" + country +
                ", badge_number='" + badge_number + '\'' +
                ", photo='" + photo + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}


