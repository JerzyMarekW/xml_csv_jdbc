package pl.com.britenet.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuration {

    private static Connection connection;

    private static final String SQLITE_URL = "jdbc:sqlite:";

    private static String filePath = "src\\main\\resources\\sqlite_file";

    public static Connection getConnection() {
        if (connection == null) {
            connectToDatabase(filePath);
        }
        return connection;
    }

    public static void connectToDatabase(String filePath) {
        try {
            if (connection == null) {
                Configuration.filePath = filePath;
                connection = DriverManager.getConnection(SQLITE_URL + filePath);
            } else {
                System.out.println("Already connected");
            }
        } catch (SQLException e) {
            System.out.println("Could not connect to SQLite file: " + filePath);
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }

    public static void createTables() {
        try {
            createCustomersTable();
            createContactsTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createCustomersTable() throws SQLException {
        String sql = "create table if not exists CUSTOMERS (\n" +
                "ID integer primary key autoincrement,\n" +
                "NAME varchar(255) not null,\n" +
                "SURNAME varchar(255) not null,\n" +
                "AGE integer\n" +
                ");";
        connection.createStatement().execute(sql);
    }

    private static void createContactsTable() throws SQLException {
        String sql = "create table if not exists CONTACTS (\n" +
                "ID integer primary key autoincrement,\n" +
                "ID_CUSTOMER integer,\n" +
                "TYPE integer,\n" +
                "CONTACT varchar(255),\n" +
                "foreign key(ID_CUSTOMER) references CUSTOMERS(ID)\n" +
                ");";
        connection.createStatement().execute(sql);
    }
}
