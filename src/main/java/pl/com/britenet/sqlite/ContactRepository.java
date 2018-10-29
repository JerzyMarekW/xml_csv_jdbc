package pl.com.britenet.sqlite;

import java.sql.*;

public class ContactRepository {

    public void createContact(int idCustomer, int type, String contact) {
        String sql = "insert into CONTACTS(ID_CUSTOMER,TYPE,CONTACT) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = Configuration.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idCustomer);
            preparedStatement.setInt(2, type);
            preparedStatement.setString(3, contact);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Configuration.closeConnection();
        }
    }

    public void readAllCustomers() {
        try {
            Connection connection = Configuration.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select ID,ID_CUSTOMER,TYPE,CONTACT from CONTACTS");
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("ID"));
                System.out.print(resultSet.getInt("ID_CUSTOMER"));
                System.out.print(resultSet.getInt("TYPE"));
                System.out.println(resultSet.getInt("CONTACT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Configuration.closeConnection();
        }
    }
}
