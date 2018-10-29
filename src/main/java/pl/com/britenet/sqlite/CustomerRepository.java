package pl.com.britenet.sqlite;

import java.sql.*;

public class CustomerRepository {

    public int createCustomer(String name, String surname, Integer age) {
        String sql = "insert into CUSTOMERS(NAME,SURNAME,AGE) values (?,?,?)";
        try {
            Connection connection = Configuration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            if (age != null) {
                preparedStatement.setInt(3, age);
            } else {
                preparedStatement.setNull(3, Types.INTEGER);
            }
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Configuration.closeConnection();
        }
        return 0;
    }

    public void readAllCustomers() {
        try {
            Connection connection = Configuration.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select ID,NAME,SURNAME,AGE from CUSTOMERS");
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("ID"));
                System.out.print(resultSet.getString("NAME"));
                System.out.print(resultSet.getString("SURNAME"));
                System.out.println(resultSet.getInt("AGE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Configuration.closeConnection();
        }
    }
}
