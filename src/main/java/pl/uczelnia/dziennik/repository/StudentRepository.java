package pl.uczelnia.dziennik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.uczelnia.dziennik.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByImie(String name);
    List<Student> findByNazwisko(String surname);
    List<Student> findByImieAndNazwisko(String name, String surname);
}
