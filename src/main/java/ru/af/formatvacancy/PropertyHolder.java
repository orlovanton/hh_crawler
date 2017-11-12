package ru.af.formatvacancy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Получение и хранение данных из application.properties
 */
public class PropertyHolder {

    public final String PATHFILE;
    public final String DB_URL;
    public final String USER;
    public final String PASSWORD;
    public final String MODE;
    public final String EXPIRIENCE;
    public final String BASE_URL;
    private static PropertyHolder instance;

    private PropertyHolder() {
        // инициализация свойств-констант
        Properties properties = loadProperties();
        PATHFILE = properties.getProperty("filepath");
        DB_URL = properties.getProperty("dburl");
        USER = properties.getProperty("user");
        PASSWORD = properties.getProperty("password");
        MODE = properties.getProperty("hh.mode");
        EXPIRIENCE= properties.getProperty("experience");
        BASE_URL= properties.getProperty("base_url");
    }

    /**
     * Дает создание единственнго экзамляра класса PropertyHolder
     * @return PropertyHolder
     */

    public static PropertyHolder getInstance() {
        if (instance == null) {
            instance = new PropertyHolder();
        }
        return instance;
    }

    /**
     * Загружает данные из файла application.properties со свойствами
     * @return prop Properties
     */
    private static Properties loadProperties() {
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
}
