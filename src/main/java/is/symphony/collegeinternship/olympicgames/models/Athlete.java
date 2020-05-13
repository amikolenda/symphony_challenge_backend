package is.symphony.collegeinternship.olympicgames.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Athlete {

    @GeneratedValue( strategy = GenerationType.AUTO )
    private int id;

    @NotNull
    @Size(min = 2)
    private String first_name;

    @NotNull
    @Size(min = 2)
    private String last_name;

    @NotNull
    private String date_of_birth;

    private String nationality;

    @NotNull
    @Id
    private String badge_number;

    private String photo;
    private String gender;
    private String role = "ATHLETE";


}
