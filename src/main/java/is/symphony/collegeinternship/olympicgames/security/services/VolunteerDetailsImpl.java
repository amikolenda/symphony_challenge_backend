package is.symphony.collegeinternship.olympicgames.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import is.symphony.collegeinternship.olympicgames.models.Country;
import is.symphony.collegeinternship.olympicgames.models.dto.SportDTO;
import is.symphony.collegeinternship.olympicgames.models.dto.VolunteerDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class VolunteerDetailsImpl implements UserDetails {
    private static final long serialVersionUID = -1079145842001304505L;

    private Long id;
    @JsonProperty("user_name")
    private String username;
    @JsonIgnore
    private String password;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    private String nationality;

    private Country country;

    private String photo;

    private String gender;
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    private Set<SportDTO> sports;

    private Collection<? extends GrantedAuthority> authorities;

    public VolunteerDetailsImpl() {
    }

    public VolunteerDetailsImpl(String username, String password,
                              Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public VolunteerDetailsImpl(Long id,String username, String password,
                              Collection<? extends GrantedAuthority> authorities, String firstName, String lastName, String nationality, Country country, String photo, String gender, String dateOfBirth, Set<SportDTO> sports) {
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
        this.id = id;
        this.sports = sports;
    }


    public static VolunteerDetailsImpl build(VolunteerDTO volunteer) {
        List<GrantedAuthority> authorities = Arrays.stream(volunteer.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new VolunteerDetailsImpl(
                volunteer.getId(),
                volunteer.getUserName(),
                volunteer.getPassword(),
                authorities,
                volunteer.getFirstName(),
                volunteer.getLastName(),
                volunteer.getNationality(),
                volunteer.getCountry(),
                volunteer.getPhoto(),
                volunteer.getGender(),
                volunteer.getDateOfBirth(),
                volunteer.getSports());
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
    @JsonProperty("date_of_birth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public Set<SportDTO> getSports() {
        return sports;
    }
}
