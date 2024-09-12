package se.lexicon.dao.impl;

import se.lexicon.model.Person;
import se.lexicon.dao.PersonDAO;
import se.lexicon.exception.MySQLException;
import se.lexicon.dao.database.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PersonDAOImpl implements PersonDAO {

    // Create a new person
    @Override
    public Person create(Person person) {
        String SQL = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new MySQLException("Creating peron failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return person;
                } else {
                    throw new MySQLException("Creating person failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while creating user: " + person, e);
        }
    }

    // Find all persons
    @Override
    public Collection<Person> findAll() {
        Collection<Person> persons = new ArrayList<>();
        String SQL = "SELECT * FROM person";
        try (Connection connection = MyConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL);) {

            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getInt("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
                persons.add(person);
            }

        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding user: " + e);
        }

        return persons;
    }

    // Find a person by ID
    @Override
    public Person findById(int personId) {
        String SQL = "SELECT person_id, first_name, last_name FROM person WHERE person_id = ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, personId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Person(
                        resultSet.getInt("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
            }

        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding a person by ID " + personId, e);
        }
        return null;
    }

    // Find persons by name (searching by first name or last name)
    @Override
    public Collection<Person> findByName(String name) {
        Collection<Person> persons = new ArrayList<>();
        String SQL = "SELECT person_id, first_name, last_name FROM person WHERE first_name LIKE ? OR last_name LIKE ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            String namePattern = "%" + name + "%";
            preparedStatement.setString(1, namePattern);
            preparedStatement.setString(2, namePattern);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getInt("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
                persons.add(person);
            }
        } catch (SQLException e) {
            throw new MySQLException("Error finding persons by name.", e);
        }
        return persons;
    }

    // Update an existing person
    @Override
    public Person update(Person person) {
        String SQL = "UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getPersonId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new MySQLException("Updating person failed, no rows affected.");
            }

            return person;

        } catch (SQLException e) {
            throw new MySQLException("Updating person failed, no rows affected.");
        }
    }

    // Delete a person by ID
    @Override
    public boolean deleteById(int personId) {
        String SQL = "DELETE FROM person WHERE person_id = ?";
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, personId);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new MySQLException("Error deleting person by ID.", e);
        }
    }
}
