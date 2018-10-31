package pl.com.britenet.parsers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.com.britenet.enities.Contact;
import pl.com.britenet.enities.Customer;
import pl.com.britenet.sqlite.Configuration;
import pl.com.britenet.sqlite.ContactRepository;
import pl.com.britenet.sqlite.CustomerRepository;

import java.io.File;
import java.util.List;

public class ParserTest {

    private CustomerRepository customerRepository = new CustomerRepository();
    private ContactRepository contactRepository = new ContactRepository();

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
    public void xmlParserTest() {
        String xmlFilePath = "src\\test\\test_resources\\dane-osoby.xml";
        XMLParser xmlParser = new XMLParser();

        xmlParser.parseFile(xmlFilePath);

        List<Customer> customers = customerRepository.readAllCustomers();
        List<Contact> contacts = contactRepository.readAllContacts();

        Assert.assertEquals(2, customers.size());
        Assert.assertEquals("Jan", customers.get(0).getName());
        Assert.assertEquals("Kowalski", customers.get(0).getSurname());
        Assert.assertEquals(Integer.valueOf(12), customers.get(0).getAge());
        Assert.assertEquals("Adam", customers.get(1).getName());
        Assert.assertEquals("Nowak", customers.get(1).getSurname());
        Assert.assertNull(customers.get(1).getAge());

        Assert.assertEquals(8, contacts.size());

    }

    @Test
    public void csvParserTest() {
        String xmlFilePath = "src\\test\\test_resources\\dane-osoby.txt";
        CSVParser csvParser = new CSVParser();
        csvParser.parseFile(xmlFilePath);
        List<Customer> customers = customerRepository.readAllCustomers();
        List<Contact> contacts = contactRepository.readAllContacts();

        Assert.assertEquals(2, customers.size());
        Assert.assertEquals("Jan", customers.get(0).getName());
        Assert.assertEquals("Kowalski", customers.get(0).getSurname());
        Assert.assertEquals(Integer.valueOf(12), customers.get(0).getAge());
        Assert.assertEquals("Adam", customers.get(1).getName());
        Assert.assertEquals("Nowak", customers.get(1).getSurname());
        Assert.assertNull(customers.get(1).getAge());

        Assert.assertEquals(8, contacts.size());
    }
}
