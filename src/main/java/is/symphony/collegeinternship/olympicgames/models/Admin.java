package is.symphony.collegeinternship.olympicgames.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Table(name = "ADMIN")
@Entity
public class Admin {
    @Column(name = "user_name")
    @NotNull
    @Id
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role = "ADMIN";

    public Admin() {
    }
    public Admin(@NotNull String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public Admin setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Admin setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public Admin setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
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
