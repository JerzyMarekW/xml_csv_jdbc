package pl.com.britenet.sqlite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.com.britenet.enities.Contact;
import pl.com.britenet.enities.Customer;

import java.io.File;
import java.util.List;

public class RepositoryTest {

    private static Customer customer;
    private static Contact contact;
    private static CustomerRepository customerRepository;
    private static ContactRepository contactRepository;

    @BeforeClass
    public static void prepareTests() {
        customer = new Customer();
        customer.setName("TEST_NAME");
        customer.setSurname("TEST_SURNAME");
        customer.setAge(50);
        customerRepository = new CustomerRepository();
        contact = new Contact();
        contact.setIdCustomer(1);
        contact.setType(1);
        contact.setContact("test@mail.pl");
        contactRepository = new ContactRepository();
    }

    @Before
    public void prepareDatabase() {
        String databaseFilePath = "src\\test\\test_resources\\sqlite_file";

        File databaseFile = new File(databaseFilePath);
        if (databaseFile.exists()) {
            databaseFile.delete();
        }
        Configuration.connectToDatabase(databaseFilePath);
        Configuration.createTables();
        Configuration.closeConnection();
    }

    @Test
    public void createCustomerTest() {
        customerRepository.createCustomer(customer);

        List<Customer> customers = customerRepository.readAllCustomers();
        Assert.assertEquals(1, customers.size());
        Assert.assertEquals(customer, customers.get(0));
    }

    @Test
    public void createContactTest() {
        contactRepository.createContact(contact);

        List<Contact> contacts = contactRepository.readAllContacts();
        Assert.assertEquals(1, contacts.size());
        Assert.assertEquals(contact, contacts.get(0));
    }
}
