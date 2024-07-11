package se.lexicon.data.todoitemtask;

import se.lexicon.model.TodoItemTask;
import java.util.List;

public interface TodoItemTaskDAO {
    void persist(TodoItemTask todoItemTask);
    TodoItemTask findById(int id);
    List<TodoItemTask> findAll();
    List<TodoItemTask> findByAssignedStatus(boolean assigned);
    List<TodoItemTask> findByPersonId(int personId);
    boolean remove(TodoItemTask todoItemTask);
}
