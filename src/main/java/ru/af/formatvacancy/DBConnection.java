package ru.af.formatvacancy;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * Предоставлет соедиенение с БД
 */
abstract class DBConnection {

    /**
     * Установить соедиенеие с БД
     *
     * @return соединение
     * @throws SQLException
     */
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                PropertyHolder.getInstance().DB_URL,
                PropertyHolder.getInstance().USER,
                PropertyHolder.getInstance().PASSWORD);
    }
}
