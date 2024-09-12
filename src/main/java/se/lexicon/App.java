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
        if (persondaoimpl.deleteById(3)) {
            System.out.println("person Deleted successfully");
        } else {
            System.out.println("Person with this ID not found");
        }*/


        // TodoItemDAOImpl test
        TodoItemDAOImpl todoItemDAO = new TodoItemDAOImpl();

        // Create a new todoitem
        TodoItem createdTodoItem = todoItemDAO.create(new TodoItem("Finish Homework", "Complete Java assignment", LocalDate.now().plusDays(5), false, 7));
        System.out.println("Created todo item successfully");

        // Find all todoitem
        System.out.println("All Todo items: " + todoItemDAO.findAll());

        // Find person by ID
        System.out.println("Found Todo item: " + todoItemDAO.findById(1));

        // Find By Done Status
        System.out.println("Found Todo item: " + todoItemDAO.findByDoneStatus(true));

        // Delete todoitem
        if (todoItemDAO.deleteById(3)) {
            System.out.println("Todo item Deleted successfully");
        } else {
            System.out.println("Todo item with this ID not found");
        }
    }
}

