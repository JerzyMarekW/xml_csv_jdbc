package pl.com.britenet.parsers;

import pl.com.britenet.sqlite.ContactRepository;
import pl.com.britenet.sqlite.CustomerRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class XMLParser {

    private CustomerRepository customerRepository = new CustomerRepository();
    private ContactRepository contactRepository = new ContactRepository();

    public void parseFile(String filePath) {
        String currentLine, currentUTFLine;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((currentLine = reader.readLine()) != null) {
                currentUTFLine = new String(currentLine.getBytes(), StandardCharsets.UTF_8);

            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file: " + filePath + "!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
