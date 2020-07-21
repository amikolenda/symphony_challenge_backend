package is.symphony.collegeinternship.olympicgames.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import is.symphony.collegeinternship.olympicgames.models.Athlete;
import is.symphony.collegeinternship.olympicgames.models.Country;
import is.symphony.collegeinternship.olympicgames.models.Sport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AthleteDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 5546003012463585704L;

    private Long id;

    @JsonIgnore
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private String username;

    @JsonIgnore
    private String password;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    private String nationality;

    private Country country;
    @JsonProperty("badge_number")
    private String badgeNumber;

    private String photo;

    private String gender;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    private Collection<? extends GrantedAuthority> authorities;

    private Set<Sport> sports;

    public AthleteDetailsImpl() {
    }

    public AthleteDetailsImpl(String username, String password,
                              Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = passwordEncoder.encode(password);
        this.authorities = authorities;
    }

    public AthleteDetailsImpl(Long id,String username, String password,
                              Collection<? extends GrantedAuthority> authorities, String firstName, String lastName,String dateOfBirth, String nationality, Country country,String badgeNumber, String photo, String gender,Set<Sport> sports) {
        this.username = username;
        this.password = passwordEncoder.encode(password);
        this.authorities = authorities;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.country = country;
        this.photo = photo;
        this.gender = gender;
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.badgeNumber = badgeNumber;
        this.sports = sports;
    }


    public static AthleteDetailsImpl build(Athlete athlete) {
        List<GrantedAuthority> authorities = Arrays.stream(athlete.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new AthleteDetailsImpl(
                athlete.getId(),
                athlete.getBadgeNumber(),
                athlete.getDateOfBirth(),
                authorities,
                athlete.getFirstName(),
                athlete.getLastName(),
                athlete.getDateOfBirth(),
                athlete.getNationality(),
                athlete.getCountry(),
                athlete.getBadgeNumber(),
                athlete.getPhoto(),
                athlete.getGender(),
                athlete.getSports());
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
        AthleteDetailsImpl that = (AthleteDetailsImpl) o;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }
    @JsonProperty("last_name")
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

    public Long getId() {
        return id;
    }
    @JsonProperty("date_of_birth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    @JsonProperty("badge_number")
    public String getBadgeNumber() {
        return badgeNumber;
    }

    public Set<Sport> getSports() {
        return sports;
    }
}
