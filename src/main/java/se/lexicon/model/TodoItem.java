package se.lexicon.model;

import java.time.LocalDate;

public class TodoItem {
    private int id;
    private String title;
    private String taskDescription;
    private LocalDate deadline;
    private boolean done;
    private Person creator;

    // Constructor
    public TodoItem(int id, String title, String taskDescription, LocalDate deadline, boolean done, Person creator) {
        if (title == null || title.isEmpty() || deadline == null || creator == null) {
            throw new IllegalArgumentException("Title, deadline, and creator cannot be null or empty");
        }
        this.id = id;
        this.title = title;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.done = done;
        this.creator = creator;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline == null) {
            throw new IllegalArgumentException("Deadline cannot be null");
        }
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        if (creator == null) {
            throw new IllegalArgumentException("Creator cannot be null");
        }
        this.creator = creator;
    }

    // Method to get summary of the TodoItem object
    public String getSummary() {
        return "TodoItem{id: " + id + ", title: " + title + ", description: " + taskDescription +
                ", deadline: " + deadline + ", done: " + done + ", creator: " + creator.getSummary() + "}";
    }

    // Method to check if the TodoItem is overdue
    public boolean isOverdue() {
        return LocalDate.now().isAfter(deadline);
    }
}
