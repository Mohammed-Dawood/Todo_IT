package se.lexicon.model;

public class TodoItemTask {
    private int id;
    private boolean assigned;
    private TodoItem todoItem;
    private Person assignee;

    // Constructor
    public TodoItemTask(int id, TodoItem todoItem, Person assignee) {
        if (todoItem == null) {
            throw new IllegalArgumentException("TodoItem cannot be null");
        }
        this.id = id;
        this.todoItem = todoItem;
        this.assignee = assignee;
        this.assigned = assignee != null;
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

    private void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(TodoItem todoItem) {
        if (todoItem == null) {
            throw new IllegalArgumentException("TodoItem cannot be null");
        }
        this.todoItem = todoItem;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
        this.assigned = assignee != null;
    }

    // Method to get summary of the TodoItemTask object
    public String getSummary() {
        return "TodoItemTask{id: " + id + ", assigned: " + assigned +
                ", todoItem: " + todoItem.getSummary() +
                ", assignee: " + (assignee != null ? assignee.getSummary() : "null") + "}";
    }
}
