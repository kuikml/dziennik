package pl.uczelnia.dziennik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.uczelnia.dziennik.entity.Nauczyciel;
import pl.uczelnia.dziennik.entity.Student;
import pl.uczelnia.dziennik.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("")
    public Student addStudent(@RequestBody Student student){
        return service.addStudent(student);
    }

    @GetMapping("")
    public List<Student> getStudents(){
        return service.getStudents();
    }

    @GetMapping("/imie/{name}")
    public ResponseEntity<List<Student>> getStudentByName(@PathVariable String name){
        return new ResponseEntity<List<Student>>(service.getStudentByName(name), HttpStatus.OK);
    }
    @GetMapping("/nazwisko/{surname}")
    public ResponseEntity<List<Student>> getStudentBySurname(@PathVariable String surname){
        return new ResponseEntity<List<Student>>(service.getStudentBySurname(surname), HttpStatus.OK);
    }
    @GetMapping("/osoba")
    public ResponseEntity<List<Student>> getStudentByName(@RequestParam String name, String surname){
        return new ResponseEntity<List<Student>>(service.getStudentByPerson(name, surname), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id){
        return service.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable int id){
        return service.updateStudent(student, id);
    }

    @GetMapping("/{sort}")
    public List<Student> findAllPages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size, @PathVariable String sort){
        return service.pages(page, size, sort);
    }
    @PostMapping("/studenci/{studentId}/nauczyciele")
    public ResponseEntity<Nauczyciel> addNauczyciel(@PathVariable(value = "studentId") int studentId, @RequestBody Nauczyciel nauczycielRequest){
        return new ResponseEntity<>(service.addNauczyciel(studentId, nauczycielRequest), HttpStatus.CREATED);
    }
}
