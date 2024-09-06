package se.lexicon.model;

import java.util.Objects;
import java.time.LocalDate;

public class TodoItem {
    private int id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Person creator;

    // Constructor
    public TodoItem(int id, String title, String description, LocalDate deadLine, boolean done, Person creator) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setDeadLine(deadLine);
        setDone(done);
        setCreator(creator);
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
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("title is null or empty.");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadline) {
        if (deadline == null) {
            throw new IllegalArgumentException("Deadline cannot be null");
        }
        this.deadLine = deadline;
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
        if (creator == null)
            throw new IllegalArgumentException("creator is null.");
        this.creator = creator;
    }

    // Method to check if the TodoItem is overdue
    public boolean isOverdue() {
        return LocalDate.now().isAfter(deadLine);
    }

    // Override toString() excluding Person object
    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadLine +
                ", done=" + done +
                '}';
    }

    // Override equals() and hashCode() excluding Person object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return id == todoItem.id &&
                done == todoItem.done &&
                title.equals(todoItem.title) &&
                Objects.equals(description, todoItem.description) &&
                deadLine.equals(todoItem.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, deadLine, done);
    }
}