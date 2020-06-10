package is.symphony.collegeinternship.olympicgames.models.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AdminDTO {
    @NotNull
    @Id
    private String userName;
    private String password;
    private String role = "ADMIN";

    public AdminDTO() {
    }
    public AdminDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public AdminDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AdminDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public AdminDTO setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminDTO admin = (AdminDTO) o;
        return Objects.equals(userName, admin.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userName='" + userName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
