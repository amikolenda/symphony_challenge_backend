package is.symphony.collegeinternship.olympicgames.security.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import is.symphony.collegeinternship.olympicgames.models.Country;
import is.symphony.collegeinternship.olympicgames.models.Sport;
import is.symphony.collegeinternship.olympicgames.models.dto.SportDTO;

import java.util.List;
import java.util.Set;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Long id;
    private String username;
    private List<String> roles;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("badge_number")
    private String badgeNumber;

    private String nationality;

    private Country country;

    private String photo;

    private String gender;
    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    private Set<SportDTO> sports;

    public JwtAuthenticationResponse(String accessToken,Long id, String username, List<String> roles,  String firstName, String lastName,String dateOfBirth, String nationality, Country country,String badgeNumber, String photo, String gender, Set<SportDTO> sports) {
        this.accessToken = accessToken;
        this.username = username;
        this.roles = roles;
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
    public JwtAuthenticationResponse(String accessToken,Long id, String username, List<String> roles,  String firstName, String lastName, String nationality, Country country, String photo, String gender, String dateOfBirth,Set<SportDTO> sports) {
        this.accessToken = accessToken;
        this.username = username;
        this.roles = roles;
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

    public JwtAuthenticationResponse(String accessToken, String username, List<String> roles) {
        this.accessToken = accessToken;
        this.username = username;
        this.roles = roles;
    }


    public JwtAuthenticationResponse setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public JwtAuthenticationResponse setTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public JwtAuthenticationResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public JwtAuthenticationResponse setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public JwtAuthenticationResponse setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public JwtAuthenticationResponse setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public JwtAuthenticationResponse setNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public JwtAuthenticationResponse setCountry(Country country) {
        this.country = country;
        return this;
    }

    public JwtAuthenticationResponse setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public JwtAuthenticationResponse setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public JwtAuthenticationResponse setId(Long id) {
        this.id = id;
        return this;
    }
    @JsonProperty("badge_number")
    public String getBadgeNumber() {
        return badgeNumber;
    }

    public JwtAuthenticationResponse setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
        return this;
    }

    public JwtAuthenticationResponse setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public Set<SportDTO> getSports() {
        return sports;
    }

    public JwtAuthenticationResponse setSports(Set<SportDTO> sports) {
        this.sports = sports;
        return this;
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
}
