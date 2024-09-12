package se.lexicon;

import se.lexicon.dao.impl.TodoItemDAOImpl;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        System.out.println("It Is Working....");

        /*// PersonDAOImpl test
        PersonDAOImpl persondaoimpl = new PersonDAOImpl();

        // Create a new person
        persondaoimpl.create(new Person("Mohammed", "Dawood"));
        System.out.println("Created person successfully");

        // Find all persons
        System.out.println("All persons: " + persondaoimpl.findAll());

        // Find person by ID
        System.out.println("Found person: " + persondaoimpl.findById(1));

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
        }*/


        // TodoItemDAOImpl test
        TodoItemDAOImpl todoItemDAO = new TodoItemDAOImpl();

        // Create a new todoitem
        TodoItem createdTodoItem = todoItemDAO.create(new TodoItem("Finish Homework", "Complete Java assignment", LocalDate.now().plusDays(5), false, 7));
        System.out.println("Created todo item successfully");

        // Find all todoitem
        System.out.println("All persons: " + todoItemDAO.findAll());

        // Find person by ID
        System.out.println("Found person: " + todoItemDAO.findById(1));
    }
}

