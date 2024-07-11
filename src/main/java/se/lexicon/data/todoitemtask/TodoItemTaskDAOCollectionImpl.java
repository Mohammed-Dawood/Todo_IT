package se.lexicon.data.todoitemtask;

import se.lexicon.model.TodoItemTask;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoItemTaskDAOCollectionImpl implements TodoItemTaskDAO {
    private List<TodoItemTask> todoItemTasks = new ArrayList<>();

    @Override
    public void persist(TodoItemTask todoItemTask) {
        if (todoItemTask == null) throw new IllegalArgumentException("TodoItemTask cannot be null");
        todoItemTasks.add(todoItemTask);
    }

    @Override
    public TodoItemTask findById(int id) {
        return todoItemTasks.stream()
                .filter(todoItemTask -> todoItemTask.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<TodoItemTask> findAll() {
        return new ArrayList<>(todoItemTasks);
    }

    @Override
    public List<TodoItemTask> findByAssignedStatus(boolean assigned) {
        return todoItemTasks.stream()
                .filter(todoItemTask -> todoItemTask.isAssigned() == assigned)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoItemTask> findByPersonId(int personId) {
        return todoItemTasks.stream()
                .filter(todoItemTask -> todoItemTask.getAssignee() != null && todoItemTask.getAssignee().getId() == personId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean remove(TodoItemTask todoItemTask) {
        if (todoItemTask == null) throw new IllegalArgumentException("TodoItemTask cannot be null");
        return todoItemTasks.remove(todoItemTask);
    }
}
