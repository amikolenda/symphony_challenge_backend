package is.symphony.collegeinternship.olympicgames.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Table(name = "SPORT")
@Entity
public class Sport implements Serializable {

    private static final long serialVersionUID = -5508644022892597453L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "sport_name", unique = true)
    private String name;
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "sport_athlete",
            joinColumns = @JoinColumn(name = "sport_id",referencedColumnName = "id",
                    nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id",
                    nullable = false, updatable = false))
    private Set<Athlete> athletes;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "sport_volunteer",
            joinColumns = @JoinColumn(name = "sport_id",referencedColumnName = "id",
                    nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "volunteer_id",referencedColumnName = "id",
                    nullable = false, updatable = false))
    private Set<Volunteer> volunteers;


    public Sport() {
    }

    public Sport(@NotNull String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Sport(@NotNull String name) {
        this.name = name;
    }
    public Sport(@NotNull String name, String description,Set<Athlete> athletes) {
        this.name = name;
        this.description = description;
        this.athletes = athletes;
    }

    public Long getId() {
        return id;
    }

    public Sport setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Sport setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Sport setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Athlete> getAthletes() {
        return athletes;
    }

    public Sport setAthletes(Set<Athlete> athletes) {
        this.athletes = athletes;
        return this;
    }

    public Set<Volunteer> getVolunteers() {
        return volunteers;
    }

    public Sport setVolunteers(Set<Volunteer> volunteers) {
        this.volunteers = volunteers;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sport)) return false;
        Sport sport = (Sport) o;
        return getName().equals(sport.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Sport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
