package se.lexicon.data.person;

import se.lexicon.model.Person;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOCollectionImpl implements PersonDAO {
    private List<Person> persons = new ArrayList<>();

    @Override
    public void persist(Person person) {
        if (person == null) throw new IllegalArgumentException("Person cannot be null");
        persons.add(person);
    }

    @Override
    public Person findById(int id) {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Person findByEmail(String email) {
        return persons.stream()
                .filter(person -> person.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(persons);
    }

    @Override
    public boolean remove(Person person) {
        if (person == null) throw new IllegalArgumentException("Person cannot be null");
        return persons.remove(person);
    }
}
