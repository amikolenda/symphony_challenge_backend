package is.symphony.collegeinternship.olympicgames.security.payload;

import is.symphony.collegeinternship.olympicgames.models.Country;

import java.util.List;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private List<String> roles;
    private String firstName;

    private String lastName;

    private String nationality;

    private Country country;

    private String photo;

    private String gender;
    private String dateOfBirth;

    public JwtAuthenticationResponse(String accessToken, String username, List<String> roles,  String firstName, String lastName, String nationality, Country country, String photo, String gender) {
        this.accessToken = accessToken;
        this.username = username;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.country = country;
        this.photo = photo;
        this.gender = gender;
    }
    public JwtAuthenticationResponse(String accessToken, String username, List<String> roles,  String firstName, String lastName, String nationality, Country country, String photo, String gender, String dateOfBirth) {
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
