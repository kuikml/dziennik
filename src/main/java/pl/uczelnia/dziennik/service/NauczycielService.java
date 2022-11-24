package pl.uczelnia.dziennik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.uczelnia.dziennik.entity.Nauczyciel;
import pl.uczelnia.dziennik.repository.NauczycielRepository;

import java.util.List;

@Service
public class NauczycielService {

    @Autowired
    private NauczycielRepository repository;

    public Nauczyciel addNauczyciel(Nauczyciel nauczyciel){
        return repository.save(nauczyciel);
    }

    public List<Nauczyciel> getNauczyciele(){
        return repository.findAll();
    }
    public Nauczyciel getNauczycielById(int id){
        return repository.findById(id).orElse(null);
    }

    public List<Nauczyciel> getNauczycielByName(String name){
        return repository.findByImie(name);
    }
    public List<Nauczyciel> getNauczycielBySurname(String surname){
        return repository.findByNazwisko(surname);
    }

    public List<Nauczyciel> getNauczycielByPerson(String name, String surname){
        return repository.findByImieAndNazwisko(name, surname);
    }

    public String deleteNauczyciel(int id){
        repository.deleteById(id);
        return "Student deleted";
    }

    public Nauczyciel updateNauczyciel(Nauczyciel nauczyciel, int id){
        Nauczyciel existing = repository.findById(id).get();
        existing.setImie(nauczyciel.getImie());
        existing.setNazwisko(nauczyciel.getNazwisko());
        existing.setWiek(nauczyciel.getWiek());
        existing.setEmail(nauczyciel.getEmail());
        existing.setPrzedmiot(nauczyciel.getPrzedmiot());
        return repository.save(existing);
    }
    public List<Nauczyciel> pages(int page, int size, String sort){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort));
        return repository.findAll(pageRequest).getContent();
    }
}
