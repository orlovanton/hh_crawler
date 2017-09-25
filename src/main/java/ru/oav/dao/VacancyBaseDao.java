package ru.oav.dao;

import ru.oav.util.PropertyHolder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by antonorlov on 25/09/2017.
 */
public abstract class VacancyBaseDao {

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                PropertyHolder.getInstance().getDbUrl(),
                PropertyHolder.getInstance().getDbUser(),
                PropertyHolder.getInstance().getDbPassword());
    }
}
