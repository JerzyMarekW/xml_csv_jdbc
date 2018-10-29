package pl.com.britenet;

import pl.com.britenet.parsers.CSVParser;
import pl.com.britenet.sqlite.Configuration;

import java.sql.SQLException;

public class FileReader {
    public static void main(String[] args) throws SQLException {
        String databaseFilePath = "src\\main\\resources\\sqlite_file";
        String csvFilePath = "src\\main\\resources\\dane-osoby.txt";
        Configuration.connectToDatabase(databaseFilePath);
        Configuration.createTables();
        Configuration.closeConnection();
        CSVParser csvParser = new CSVParser();
        csvParser.parseFile(csvFilePath);

    }
}
