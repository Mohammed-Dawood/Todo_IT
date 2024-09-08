package se.lexicon.dao.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class MyConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/todoit";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234567890";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }
}
