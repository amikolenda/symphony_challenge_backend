package is.symphony.collegeinternship.olympicgames.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import is.symphony.collegeinternship.olympicgames.models.Country;
import is.symphony.collegeinternship.olympicgames.models.Volunteer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class VolunteerDetailsImpl implements UserDetails {
    private String username;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    private String nationality;

    private Country country;

    private String photo;

    private String gender;
    private String dateOfBirth;

    private Collection<? extends GrantedAuthority> authorities;

    public VolunteerDetailsImpl() {
    }

    public VolunteerDetailsImpl(String username, String password,
                              Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public VolunteerDetailsImpl(String username, String password,
                              Collection<? extends GrantedAuthority> authorities, String firstName, String lastName, String nationality, Country country, String photo, String gender, String dateOfBirth) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.country = country;
        this.photo = photo;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }


    public static VolunteerDetailsImpl build(Volunteer volunteer) {
        List<GrantedAuthority> authorities = Arrays.stream(volunteer.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new VolunteerDetailsImpl(
                volunteer.getUserName(),
                volunteer.getPassword(),
                authorities,
                volunteer.getFirstName(),
                volunteer.getLastName(),
                volunteer.getNationality(),
                volunteer.getCountry(),
                volunteer.getPhoto(),
                volunteer.getGender(),
                volunteer.getDateOfBirth());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolunteerDetailsImpl that = (VolunteerDetailsImpl) o;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public Country getCountry() {
        return country;
    }

    public String getPhoto() {
        return photo;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
