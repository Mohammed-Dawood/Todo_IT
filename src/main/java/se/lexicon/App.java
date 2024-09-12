package se.lexicon;

import se.lexicon.model.Person;
import se.lexicon.dao.impl.PersonDAOImpl;

import java.util.Collection;

public class App {
    public static void main(String[] args) {

        System.out.println("It Is Working....");

        // PersonDAOImpl test
        PersonDAOImpl persondaoimpl = new PersonDAOImpl();

        // Create a new person
        persondaoimpl.create(new Person("Mohammed", "Dawood"));
        System.out.println("Created person successfully");

        // Find all persons
        System.out.println("All persons: " + persondaoimpl.findAll());

        // Find person by ID
        Person foundPersonById = persondaoimpl.findById(1);
        System.out.println("Found person: " + foundPersonById);

        // findByName
        Collection<Person> foundPersonByName = persondaoimpl.findByName("dawood");
        System.out.println("Found person: " + foundPersonByName);

        // Update person
        Person NewPerson = persondaoimpl.update(new Person(5, "Test", "Testson"));
        System.out.println("Updated person: " + NewPerson);

        // Delete person
        boolean deleted = persondaoimpl.deleteById(3);
        if (deleted) {
            System.out.println("person Deleted successfully: ");
        } else {
            System.out.println("Person with this ID not found ");
        }

    }
}

