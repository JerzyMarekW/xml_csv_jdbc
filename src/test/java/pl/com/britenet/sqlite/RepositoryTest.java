package pl.com.britenet.sqlite;

import org.junit.Before;
import org.junit.Test;

public class RepositoryTest {

    @Before
    public void prepareDatabase() {
        String databaseFilePath = "src\\test\\test_resources\\dane-osoby.xml";
        Configuration.connectToDatabase(databaseFilePath);
        Configuration.createTables();
        Configuration.closeConnection();
    }

    @Test
    public void createCustomerTest() {

    }

    @Test
    public void createContactTest() {

    }
}
