package pl.com.britenet.parsers;

import pl.com.britenet.enities.Contact;
import pl.com.britenet.enities.Customer;
import pl.com.britenet.sqlite.ContactRepository;
import pl.com.britenet.sqlite.CustomerRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CSVParser implements Parser {

    private CustomerRepository customerRepository = new CustomerRepository();
    private ContactRepository contactRepository = new ContactRepository();

    public void parseFile(String filePath) {
        String currentLine;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((currentLine = reader.readLine()) != null) {
                parseLine(new String(currentLine.getBytes(), StandardCharsets.UTF_8));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file: " + filePath + "!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseLine(String line) {
        String[] splitLine = line.split(",");
        Integer age = null;
        if (splitLine[2].length() > 0) {
            try {
                age = Integer.parseInt(splitLine[2]);
            } catch (NumberFormatException e) {
                age = null;
            }
        }
        int generatedId = customerRepository.createCustomer(new Customer(null, splitLine[0], splitLine[1], age));
        for (int i = 4; i < splitLine.length; i++) {
            contactRepository.createContact(new Contact(null, generatedId, detectContactType(splitLine[i]), splitLine[i]));
        }
    }

    private int detectContactType(String contact) {
        //TODO
        // nie da się odróżnić typu jabber od zwykłego username
        if (contact.contains("@jabber")) {
            return 3;
        }
        //TODO
        // nie da się odróżnić numeru telefonu od mp. numeru icq
        if (contact.matches("(\\d+[\\s]?)*\\d+")) {
            return 2;
        }
        if (contact.contains("@")) {
            return 1;
        }
        return 0;
    }
}
