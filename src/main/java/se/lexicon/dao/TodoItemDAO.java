package se.lexicon.dao;

import java.util.Collection;

import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

public interface TodoItemDAO {

    TodoItem create(TodoItem todoItem);
    Collection<TodoItem> findAll();
    TodoItem findById(int id);
    Collection<TodoItem> findByDoneStatus(boolean doneStatus);
    Collection<TodoItem> findByAssignee(int id);
    Collection<TodoItem> findByAssignee(Person person);
    Collection<TodoItem> findByUnassignedTodItems();
    TodoItem update(TodoItem todoItem);
    boolean deleteById(int id);

}
