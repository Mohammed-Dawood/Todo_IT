package se.lexicon.model;

import java.time.LocalDate;

public class TodoItemTask {
    private int id;
    private boolean assigned; // Encapsulated boolean
    private String todoItem;
    private Person assignee;

    // Constructor
    public TodoItemTask(int id, String todoItem, Person assignee) {
        if (todoItem == null || assignee == null) {
            throw new IllegalArgumentException("TodoItem and assignee cannot be null");
        }
        this.id = id;
        this.todoItem = todoItem;
        this.assignee = assignee;
        this.assigned = true; // Automatically set to true if assignee is not null
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public String getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(String todoItem) {
        if (todoItem == null) {
            throw new IllegalArgumentException("TodoItem cannot be null");
        }
        this.todoItem = todoItem;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        if (assignee == null) {
            throw new IllegalArgumentException("Assignee cannot be null");
        }
        this.assignee = assignee;
        this.assigned = true; // Ensure assigned is true if assignee is set
    }

    // Method to get summary of the TodoItemTask object
    public String getSummary() {
        return "TodoItemTask{id: " + id + ", assigned: " + assigned + ", todoItem: " + todoItem +
                ", assignee: " + assignee.getSummary() + "}";
    }
}
