package pl.com.britenet;

import pl.com.britenet.parsers.CSVParser;
import pl.com.britenet.parsers.XMLParser;
import pl.com.britenet.sqlite.Configuration;

public class FileReader {
    public static void main(String[] args) {
        String databaseFilePath = "src\\main\\resources\\sqlite_file";
        String csvFilePath = "src\\main\\resources\\dane-osoby.txt";
        String xmlFilePath = "src\\main\\resources\\dane-osoby.xml";
        Configuration.connectToDatabase(databaseFilePath);
        Configuration.createTables();
        Configuration.closeConnection();
        CSVParser csvParser = new CSVParser();
        csvParser.parseFile(csvFilePath);
        XMLParser xmlParser = new XMLParser();
        xmlParser.parseFile(xmlFilePath);
    }
}
