package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataReader {

    private Properties properties;

    public TestDataReader() throws IOException {

        properties = new Properties();

        FileInputStream file = new FileInputStream(
                "src/test/resources/testdata.properties"
        );

        properties.load(file);
        file.close();
    }

    public String getTestData(String key) {
        return properties.getProperty(key);
    }
}