package se.lexicon.dao.impl;

import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;
import se.lexicon.dao.TodoItemDAO;
import se.lexicon.dao.database.MyConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class TodoItemDAOImpl implements TodoItemDAO {

    // SQL Queries
    private static final String INSERT_TODOITEM_SQL =
            "INSERT INTO todo_item (title, description, deadline, done, assignee_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_TODOITEM_BY_ID =
            "SELECT * FROM todo_item WHERE todo_id = ?";
    private static final String SELECT_ALL_TODOITEMS =
            "SELECT * FROM todo_item";
    private static final String SELECT_TODOITEMS_BY_DONE_STATUS =
            "SELECT * FROM todo_item WHERE done = ?";
    private static final String SELECT_TODOITEMS_BY_ASSIGNEE_ID =
            "SELECT * FROM todo_item WHERE assignee_id = ?";
    private static final String SELECT_TODOITEMS_BY_UNASSIGNED =
            "SELECT * FROM todo_item WHERE assignee_id IS NULL";
    private static final String UPDATE_TODOITEM_SQL =
            "UPDATE todo_item SET title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? WHERE todo_id = ?";
    private static final String DELETE_TODOITEM_BY_ID_SQL =
            "DELETE FROM todo_item WHERE todo_id = ?";

    @Override
    public TodoItem create(TodoItem todoItem) {
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_TODOITEM_SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, todoItem.getTitle());
            stmt.setString(2, todoItem.getDescription());
            stmt.setDate(3, Date.valueOf(todoItem.getDeadline()));
            stmt.setBoolean(4, todoItem.isDone());
            if (todoItem.getAssignee() != null) {
                stmt.setInt(5, todoItem.getAssignee().getPersonId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    todoItem.setTodoId(generatedKeys.getInt(1));
                }
            }

            return todoItem;

        } catch (SQLException e) {
            throw new RuntimeException("Error creating TodoItem", e);
        }
    }

    @Override
    public Collection<TodoItem> findAll() {
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_TODOITEMS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TodoItem todoItem = mapResultSetToTodoItem(rs);
                todoItems.add(todoItem);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all TodoItems", e);
        }
        return todoItems;
    }

    @Override
    public TodoItem findById(int todoId) {
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_TODOITEM_BY_ID)) {

            stmt.setInt(1, todoId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTodoItem(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding TodoItem by ID", e);
        }
        return null;
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean doneStatus) {
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_TODOITEMS_BY_DONE_STATUS)) {

            stmt.setBoolean(1, doneStatus);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TodoItem todoItem = mapResultSetToTodoItem(rs);
                    todoItems.add(todoItem);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding TodoItems by done status", e);
        }
        return todoItems;
    }

    @Override
    public Collection<TodoItem> findByAssignee(int todoId) {
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_TODOITEMS_BY_ASSIGNEE_ID)) {

            stmt.setInt(1, todoId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TodoItem todoItem = mapResultSetToTodoItem(rs);
                    todoItems.add(todoItem);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding TodoItems by assignee ID", e);
        }
        return todoItems;
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person person) {
        return findByAssignee(person.getPersonId());
    }

    @Override
    public Collection<TodoItem> findByUnassignedTodItems() {
        Collection<TodoItem> todoItems = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_TODOITEMS_BY_UNASSIGNED);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TodoItem todoItem = mapResultSetToTodoItem(rs);
                todoItems.add(todoItem);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding unassigned TodoItems", e);
        }
        return todoItems;
    }

    @Override
    public TodoItem update(TodoItem todoItem) {
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_TODOITEM_SQL)) {

            stmt.setString(1, todoItem.getTitle());
            stmt.setString(2, todoItem.getDescription());
            stmt.setDate(3, Date.valueOf(todoItem.getDeadline()));
            stmt.setBoolean(4, todoItem.isDone());
            if (todoItem.getAssignee() != null) {
                stmt.setInt(5, todoItem.getAssignee().getPersonId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.setInt(6, todoItem.getTodoId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating TodoItem failed, no rows affected.");
            }

            return todoItem;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating TodoItem", e);
        }
    }

    @Override
    public boolean deleteById(int todoId) {
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_TODOITEM_BY_ID_SQL)) {

            stmt.setInt(1, todoId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting TodoItem", e);
        }
    }

    // Helper method to map the ResultSet to a TodoItem object
    private TodoItem mapResultSetToTodoItem(ResultSet rs) throws SQLException {
        int todoId = rs.getInt("todo_id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        LocalDate deadline = rs.getDate("deadline").toLocalDate();
        boolean done = rs.getBoolean("done");
        int assigneeId = rs.getInt("assignee_id");

        Person assignee = null; // This can be fetched from a PersonDAO if needed

        return new TodoItem(todoId, title, description, deadline, done, assignee);
    }
}
