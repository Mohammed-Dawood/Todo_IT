package se.lexicon.data.person;

import se.lexicon.model.Person;
import java.util.List;

public interface PersonDAO {
    void persist(Person person);
    Person findById(int id);
    Person findByEmail(String email);
    List<Person> findAll();
    boolean remove(Person person);
}
