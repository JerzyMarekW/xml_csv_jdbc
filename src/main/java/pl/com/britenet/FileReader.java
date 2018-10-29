package pl.com.britenet;

import pl.com.britenet.sqlite.Configuration;
import pl.com.britenet.sqlite.ContactRepository;
import pl.com.britenet.sqlite.CustomerRepository;

import java.sql.*;

public class FileReader {
    public static void main(String[] args) throws SQLException {
        String myFilePath = "src\\main\\resources\\sqlite_file";
        Configuration.connectToDatabase(myFilePath);
        Configuration.createTables();
        ContactRepository contactRepository = new ContactRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.createCustomer("Donald", "Trump", 72);
        contactRepository.createContact(1,2, "555-555-555");
        Statement statement = Configuration.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from CUSTOMERS");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("NAME"));
            System.out.println(resultSet.getString("SURNAME"));
        }
        Configuration.closeConnection();
    }
}
