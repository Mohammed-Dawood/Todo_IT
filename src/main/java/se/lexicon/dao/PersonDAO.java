package se.lexicon.dao;

import se.lexicon.model.Person;

import java.util.Collection;

public interface PersonDAO {

    Person create(Person person);
    Collection<Person> findAll();
    Person findById(int personId);
    Collection<Person> findByName(String name);
    Person update(Person person);
    boolean deleteById(int personId);

}
