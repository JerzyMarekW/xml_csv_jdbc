package pl.com.britenet.sqlite;

import pl.com.britenet.enities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    public int createCustomer(Customer customer) {
        String sql = "insert into CUSTOMERS(NAME,SURNAME,AGE) values (?,?,?)";
        try {
            Connection connection = Configuration.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            if (customer.getAge() != null) {
                preparedStatement.setInt(3, customer.getAge());
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Configuration.closeConnection();
        }
        return 0;
    }

    public List<Customer> readAllCustomers() {
        List<Customer> resultList = new ArrayList<>();
        try {
            Connection connection = Configuration.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select ID,NAME,SURNAME,AGE from CUSTOMERS");
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("ID"));
                customer.setName(resultSet.getString("NAME"));
                customer.setSurname(resultSet.getString("SURNAME"));
                customer.setAge((Integer) resultSet.getObject("AGE"));
                resultList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Configuration.closeConnection();
        }
        return resultList;
    }
}
