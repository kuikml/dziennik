package pl.uczelnia.dziennik.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 2, message = "Imię musi składać się z min. 2 znaków.")
    private String imie;
    private String nazwisko;
    @Min(18)
    private int wiek;
    @Email
    private String email;
    private String kierunek;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "student_nauczyciel",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "nauczyciel_id")})
    private Set<Nauczyciel> nauczyciels = new HashSet<>();

    public void addNauczyciel(Nauczyciel nauczyciel){
        this.nauczyciels.add(nauczyciel);
        nauczyciel.getStudents().add(this);
    }

    public void removeNauczyciel(long nauczycielId){
        Nauczyciel nauczyciel = this.nauczyciels.stream().filter(n -> n.getId() == nauczycielId).findFirst().orElse(null);
        if (nauczyciel != null){
            this.nauczyciels.remove(nauczyciel);
            nauczyciel.getStudents().remove(this);
        }
    }
}
