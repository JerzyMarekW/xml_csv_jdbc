package pl.com.britenet;

import pl.com.britenet.parsers.CSVParser;
import pl.com.britenet.parsers.Parser;
import pl.com.britenet.parsers.XMLParser;
import pl.com.britenet.sqlite.Configuration;

public class FileParser {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Podaj 2 parametry wiersza polecen");
            System.out.println("1 - sciezka do pliku");
            System.out.println("2 - typ pliku(csv lub xml)");
            return;
        }
        String databaseFilePath = "src\\main\\resources\\sqlite_file";
        String filePath = args[0];
        String fileType = args[1];
        Configuration.connectToDatabase(databaseFilePath);
        Configuration.createTables();
        Configuration.closeConnection();
        Parser parser;
        if ("csv".equals(fileType)) {
            parser = new CSVParser();
            parser.parseFile(filePath);
        } else if ("xml".equals(fileType)) {
            parser = new XMLParser();
            parser.parseFile(filePath);
        }
    }
}
