package utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = PropertiesUtils.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error loading properties file", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
