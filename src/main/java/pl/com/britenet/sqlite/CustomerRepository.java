package pl.com.britenet.sqlite;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepository {

    public void createCustomer(String name, String surname, Integer age) {
        String sql = "insert into CUSTOMERS(NAME,SURNAME,AGE) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = Configuration.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Configuration.closeConnection();
        }
    }
}
