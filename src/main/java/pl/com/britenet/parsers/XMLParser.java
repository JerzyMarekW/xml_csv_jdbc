package pl.com.britenet.parsers;

import pl.com.britenet.enities.Contact;
import pl.com.britenet.enities.Customer;
import pl.com.britenet.sqlite.ContactRepository;
import pl.com.britenet.sqlite.CustomerRepository;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class XMLParser implements Parser{

    private CustomerRepository customerRepository = new CustomerRepository();
    private ContactRepository contactRepository = new ContactRepository();

    public void parseFile(String filePath) {
        Customer customer = new Customer();
        Contact contact;
        boolean isContact = false;
        Integer customerId = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(filePath));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if ("person".equals(startElement.getName().getLocalPart())) {
                        customer = new Customer();
                        isContact = false;
                    } else if ("name".equals(startElement.getName().getLocalPart())) {
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.setName(xmlEvent.asCharacters().getData());
                    } else if ("surname".equals(startElement.getName().getLocalPart())) {
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.setSurname(xmlEvent.asCharacters().getData());
                    } else if ("age".equals(startElement.getName().getLocalPart())) {
                        xmlEvent = xmlEventReader.nextEvent();
                        customer.setAge(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if ("contacts".equals(startElement.getName().getLocalPart())) {
                        customerId = customerRepository.createCustomer(customer);
                        isContact = true;
                    } else if ("email".equals(startElement.getName().getLocalPart())) {
                        xmlEvent = xmlEventReader.nextEvent();
                        contact = new Contact();
                        contact.setIdCustomer(customerId);
                        contact.setType(1);
                        contact.setContact(xmlEvent.asCharacters().getData());
                        contactRepository.createContact(contact);
                    } else if ("phone".equals(startElement.getName().getLocalPart())) {
                        xmlEvent = xmlEventReader.nextEvent();
                        contact = new Contact();
                        contact.setIdCustomer(customerId);
                        contact.setType(2);
                        contact.setContact(xmlEvent.asCharacters().getData());
                        contactRepository.createContact(contact);
                    } else if ("jabber".equals(startElement.getName().getLocalPart())) {
                        xmlEvent = xmlEventReader.nextEvent();
                        contact = new Contact();
                        contact.setIdCustomer(customerId);
                        contact.setType(3);
                        contact.setContact(xmlEvent.asCharacters().getData());
                        contactRepository.createContact(contact);
                    } else if (isContact) {
                        xmlEvent = xmlEventReader.nextEvent();
                        contact = new Contact();
                        contact.setIdCustomer(customerId);
                        contact.setType(0);
                        contact.setContact(xmlEvent.asCharacters().getData());
                        contactRepository.createContact(contact);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
