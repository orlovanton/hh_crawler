package ru.oav.formatvacancy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by PC on 17.06.2017.
 */
public class Constanses {
    private static Properties loadProperies() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("src/main/resources/application.properties");
            prop.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    static final String PATHFILE = Constanses.loadProperies().getProperty("filepath");
    public static final String DB_URL = Constanses.loadProperies().getProperty("dburl");
    public static final String USER = Constanses.loadProperies().getProperty("user");
    public static final String PASSWORD = Constanses.loadProperies().getProperty("password");
    public static final String MODE = Constanses.loadProperies().getProperty("hh.mode");
}
