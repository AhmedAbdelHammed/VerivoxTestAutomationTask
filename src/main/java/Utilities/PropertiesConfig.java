package Utilities;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {
    Properties properties;

    public PropertiesConfig() {
        properties = new Properties();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean runHeadless(){return Boolean.parseBoolean(properties.getProperty("runHeadless"));}

    public int getWaitTimeout(){return Integer.parseInt(properties.getProperty("waitTimeout"));}

}
