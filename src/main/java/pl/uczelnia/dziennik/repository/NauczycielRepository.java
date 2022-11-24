package pl.uczelnia.dziennik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.uczelnia.dziennik.entity.Nauczyciel;
import pl.uczelnia.dziennik.entity.Student;

import java.util.List;

public interface NauczycielRepository extends JpaRepository<Nauczyciel, Integer> {
    List<Nauczyciel> findByImie(String name);
    List<Nauczyciel> findByNazwisko(String surname);
    List<Nauczyciel> findByImieAndNazwisko(String name, String surname);

}
