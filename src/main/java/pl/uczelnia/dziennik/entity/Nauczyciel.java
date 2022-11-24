package pl.uczelnia.dziennik.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nauczyciels")
public class Nauczyciel {

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
    private String przedmiot;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "nauczyciels")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    public Set<Student> getStudents(){
        return students;
    }

    public void setStudents(Set<Student> students){
        this.students = students;
    }
}
