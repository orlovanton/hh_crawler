package ru.oav.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by antonorlov on 25/09/2017.
 */
public class PropertyHolder {

    private static final String FILE_PATH = "filepath";
    private static final String HH_MODE = "hh.mode";
    private static final String DB_URL = "dburl";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";
    private static final String EXPERIENCE = "experience";
    private static final String SEARCH_QUERY = "search_query";
    private static final String BASE_URL = "base_url";

    private static PropertyHolder instance;
    private Properties properties;

    private PropertyHolder() {
        properties = loadProperties();
    }

    public static PropertyHolder getInstance() {
        if (instance == null) {
            instance = new PropertyHolder();
        }
        return instance;
    }


    public String getFilePath() {
        return (String) properties.get(FILE_PATH);
    }

    public String getDbUrl() {
        return (String) properties.get(DB_URL);
    }

    public String getDbUser() {
        return (String) properties.get(DB_USER);
    }

    public String getDbPassword() {
        return (String) properties.get(DB_PASSWORD);
    }

    public String getMode() {
        return (String) properties.get(HH_MODE);
    }

    public String getSearchQuery() {
        return (String) properties.get(SEARCH_QUERY);
    }
    
    public String getBaseUrl() {
        return (String) properties.get(BASE_URL);
    }

    public ExperienceEnum getExperience() {
        String exp = (String) properties.get(EXPERIENCE);
        if (exp == null) {
            setExperience(ExperienceEnum.NO_EXPERIENCE);
            return ExperienceEnum.NO_EXPERIENCE;
        }
        return ExperienceEnum.valueOf(exp);
    }

    public void setExperience(ExperienceEnum experience) {
        properties.put(EXPERIENCE, experience.name());
    }


    private static Properties loadProperties() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = ClassLoader.getSystemResourceAsStream("application.properties");
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
