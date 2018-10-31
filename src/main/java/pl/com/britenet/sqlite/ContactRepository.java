package pl.com.britenet.sqlite;

import pl.com.britenet.enities.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    public int createContact(Contact contact) {
        String sql = "insert into CONTACTS(ID_CUSTOMER,TYPE,CONTACT) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = Configuration.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, contact.getIdCustomer());
            preparedStatement.setInt(2, contact.getType());
            preparedStatement.setString(3, contact.getContact());
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

    public List<Contact> readAllContacts() {
        List<Contact> resultList = new ArrayList<>();
        try {
            Connection connection = Configuration.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select ID,ID_CUSTOMER,TYPE,CONTACT from CONTACTS");
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("ID"));
                contact.setIdCustomer(resultSet.getInt("ID_CUSTOMER"));
                contact.setType(resultSet.getInt("TYPE"));
                contact.setContact(resultSet.getString("CONTACT"));
                resultList.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Configuration.closeConnection();
        }
        return resultList;
    }
}
