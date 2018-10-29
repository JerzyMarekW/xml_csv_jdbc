package pl.com.britenet.sqlite;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
