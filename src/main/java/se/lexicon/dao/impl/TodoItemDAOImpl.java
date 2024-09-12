package se.lexicon.dao.impl;

import se.lexicon.exception.MySQLException;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;
import se.lexicon.dao.TodoItemDAO;
import se.lexicon.dao.database.MyConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class TodoItemDAOImpl implements TodoItemDAO {


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
        String SQL =
                "INSERT INTO todo_item (title, description, deadline, done, assignee_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, todoItem.getTitle());
            preparedStatement.setString(2, todoItem.getDescription());
            preparedStatement.setDate(3, Date.valueOf(todoItem.getDeadline()));
            preparedStatement.setBoolean(4, todoItem.isDone());
            preparedStatement.setInt(5, todoItem.getAssigneeId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new MySQLException("Creating todo item failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return todoItem;
                } else {
                    throw new MySQLException("Creating todo item failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new MySQLException("Error occurred while creating todo item: " + todoItem, e);
        }

    }

    @Override
    public Collection<TodoItem> findAll() {
        Collection<TodoItem> todoItems = new ArrayList<>();
        String SQL = "SELECT * FROM todo_item";
        try (Connection connection = MyConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL);) {

            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem(
                        resultSet.getInt("todo_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItems.add(todoItem);
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding todoitem: " + e);
        }
        return todoItems;
    }

    @Override
    public TodoItem findById(int todoId) {
        String SQL = "SELECT * FROM todo_item WHERE todo_id = ?";

        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, todoId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new TodoItem(
                        resultSet.getInt("todo_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
            }
        } catch (SQLException e) {
            throw new MySQLException("Error finding TodoItem by ID", e);
        }
        return null;
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean doneStatus) {
        Collection<TodoItem> todoItems = new ArrayList<>();
        String SQL = "SELECT * FROM todo_item WHERE done = ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setBoolean(1, doneStatus);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem(
                        resultSet.getInt("todo_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("deadline").toLocalDate(),
                        resultSet.getBoolean("done"),
                        resultSet.getInt("assignee_id")
                );
                todoItems.add(todoItem);
            }
        } catch (SQLException e) {
            throw new MySQLException("Error finding TodoItems by done status", e);
        }
        return todoItems;
    }


}
