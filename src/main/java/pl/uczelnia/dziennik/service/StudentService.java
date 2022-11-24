package pl.uczelnia.dziennik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.uczelnia.dziennik.entity.Nauczyciel;
import pl.uczelnia.dziennik.entity.Student;
import pl.uczelnia.dziennik.repository.NauczycielRepository;
import pl.uczelnia.dziennik.repository.StudentRepository;

import java.util.List;
import java.util.ResourceBundle;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private NauczycielRepository nrepository;

    public Student addStudent(Student student){
        return repository.save(student);
    }

    public List<Student> getStudents(){
        return repository.findAll();
    }
    public Student getStudentById(int id){
        return repository.findById(id).orElse(null);
    }

    public List<Student> getStudentByName(String name){
        return repository.findByImie(name);
    }
    public List<Student> getStudentBySurname(String surname){
        return repository.findByNazwisko(surname);
    }

    public List<Student> getStudentByPerson(String name, String surname){
        return repository.findByImieAndNazwisko(name, surname);
    }

    public String deleteStudent(int id){
        repository.deleteById(id);
        return "Student deleted";
    }

    public Student updateStudent(Student student, int id){
        Student existing = repository.findById(id).get();
        existing.setImie(student.getImie());
        existing.setNazwisko(student.getNazwisko());
        existing.setWiek(student.getWiek());
        existing.setEmail(student.getEmail());
        existing.setKierunek(student.getKierunek());
        return repository.save(existing);
    }
    public List<Student> pages(int page, int size, String sort){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort));
        return repository.findAll(pageRequest).getContent();
    }
    public Student createStudent;


    public Nauczyciel addNauczyciel(int studentId, Nauczyciel nauczycielRequest){
        Nauczyciel nauczyciel = repository.findById(studentId).map(student -> {
            int nauczycielId =nauczycielRequest.getId();
            if (nauczycielId != 0){
                Nauczyciel _nauczyciel = nrepository.findById(nauczycielId).orElse(null);
                student.addNauczyciel(_nauczyciel);
                repository.save(student);
                return _nauczyciel;
            }
            student.addNauczyciel(nauczycielRequest);
            return nrepository.save(nauczycielRequest);
        }).orElse(null);
        return nauczyciel;
    }

}
