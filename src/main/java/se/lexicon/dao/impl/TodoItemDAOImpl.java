package se.lexicon.dao.impl;

import se.lexicon.dao.TodoItemDAO;
import se.lexicon.model.TodoItem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoItemDAOImpl implements TodoItemDAO {
    private List<TodoItem> todoItems = new ArrayList<>();

    @Override
    public void persist(TodoItem todoItem) {
        if (todoItem == null) throw new IllegalArgumentException("TodoItem cannot be null");
        todoItems.add(todoItem);
    }

    @Override
    public TodoItem findById(int id) {
        return todoItems.stream()
                .filter(todoItem -> todoItem.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<TodoItem> findAll() {
        return new ArrayList<>(todoItems);
    }

    @Override
    public List<TodoItem> findAllByDoneStatus(boolean doneStatus) {
        return todoItems.stream()
                .filter(todoItem -> todoItem.isDone() == doneStatus)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoItem> findByTitleContains(String title) {
        return todoItems.stream()
                .filter(todoItem -> todoItem.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoItem> findByPersonId(int personId) {
        return todoItems.stream()
                .filter(todoItem -> todoItem.getCreator().getId() == personId)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoItem> findByDeadlineBefore(LocalDate date) {
        return todoItems.stream()
                .filter(todoItem -> todoItem.getDeadline().isBefore(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoItem> findByDeadlineAfter(LocalDate date) {
        return todoItems.stream()
                .filter(todoItem -> todoItem.getDeadline().isAfter(date))
                .collect(Collectors.toList());
    }

    @Override
    public boolean remove(TodoItem todoItem) {
        if (todoItem == null) throw new IllegalArgumentException("TodoItem cannot be null");
        return todoItems.remove(todoItem);
    }
}
