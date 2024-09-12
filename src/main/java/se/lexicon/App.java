package se.lexicon;

import se.lexicon.model.Person;
import se.lexicon.dao.impl.PersonDAOImpl;

public class App {
    public static void main(String[] args) {

        System.out.println("It Is Working....");

        // PersonDAOImpl test
        /*PersonDAOImpl persondaoimpl = new PersonDAOImpl();

        // Create a new person
        Person newPerson = new Person(0, "John", "Doe");
        persondaoimpl.create(newPerson);
        System.out.println("Created person: " + newPerson);

        // Find all persons
        System.out.println("All persons: " + persondaoimpl.findAll());

        // Find person by ID
        Person foundPerson = persondaoimpl.findById(newPerson.getPersonId());
        System.out.println("Found person: " + foundPerson);

        // Update person
        foundPerson.setFirstName("Jane");
        persondaoimpl.update(foundPerson);
        System.out.println("Updated person: " + persondaoimpl.findById(foundPerson.getPersonId()));

        // Delete person
        boolean deleted = persondaoimpl.deleteById(foundPerson.getPersonId());
        System.out.println("Deleted person: " + deleted);*/


        // TodoItemDAOImpl test


    }

}

