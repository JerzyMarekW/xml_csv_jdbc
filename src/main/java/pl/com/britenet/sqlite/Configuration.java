package pl.com.britenet.sqlite;

import java.sql.*;
import java.util.Optional;

public class Configuration {

    private static final String SQLITE_URL = "jdbc:sqlite:";

    public static Optional<Connection> connectToSQLiteDatabase(String fileName) {
        try {
            return Optional.ofNullable(DriverManager.getConnection(SQLITE_URL + fileName));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeStatement(Connection connection, String query) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
