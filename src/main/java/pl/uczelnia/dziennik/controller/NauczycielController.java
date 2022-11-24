package pl.uczelnia.dziennik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.uczelnia.dziennik.entity.Nauczyciel;
import pl.uczelnia.dziennik.entity.Nauczyciel;
import pl.uczelnia.dziennik.service.NauczycielService;
import pl.uczelnia.dziennik.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/nauczyciel")
public class NauczycielController {
    @Autowired
    private NauczycielService service;

    @PostMapping("")
    public Nauczyciel addNauczyciel(@RequestBody Nauczyciel nauczyciel){
        return service.addNauczyciel(nauczyciel);
    }

    @GetMapping("")
    public List<Nauczyciel> getNauczyciel(){
        return service.getNauczyciele();
    }

    @GetMapping("/imie/{name}")
    public ResponseEntity<List<Nauczyciel>> getNauczycielByName(@PathVariable String name){
        return new ResponseEntity<List<Nauczyciel>>(service.getNauczycielByName(name), HttpStatus.OK);
    }
    @GetMapping("/nazwisko/{surname}")
    public ResponseEntity<List<Nauczyciel>> getNauczycielBySurname(@PathVariable String surname){
        return new ResponseEntity<List<Nauczyciel>>(service.getNauczycielByName(surname), HttpStatus.OK);
    }
    @GetMapping("/osoba")
    public ResponseEntity<List<Nauczyciel>> getNauczycielByName(@RequestParam String name, String surname){
        return new ResponseEntity<List<Nauczyciel>>(service.getNauczycielByPerson(name, surname), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteNauczyciel(@PathVariable int id){
        return service.deleteNauczyciel(id);
    }

    @PutMapping("/{id}")
    public Nauczyciel updateNauczyciel(@RequestBody Nauczyciel nauczyciel, @PathVariable int id){
        return service.updateNauczyciel(nauczyciel, id);
    }

    @GetMapping("/{sort}")
    public List<Nauczyciel> findAllPages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "100") int size, @PathVariable String sort){
        return service.pages(page, size, sort);
    }
}
