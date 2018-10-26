package pl.com.britenet;

import pl.com.britenet.sqlite.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FileReader {
    public static void main(String[] args) throws SQLException {
        String myFilePath = "src\\main\\resources\\sqlite_file";
        Connection connection = Configuration.connectToSQLiteDatabase(myFilePath).orElseThrow(SQLException::new);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from CUSTOMERS");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("NAME"));
            System.out.println(resultSet.getString("SURNAME"));
        }
        Configuration.closeConnection(connection);
    }
}
