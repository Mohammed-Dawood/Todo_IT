package se.lexicon.dao;

import java.util.Collection;

import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

public interface TodoItemDAO {

    TodoItem create(TodoItem todoItem);
    Collection<TodoItem> findAll();


}
