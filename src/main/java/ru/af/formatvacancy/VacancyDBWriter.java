package ru.af.formatvacancy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;


/**
 * Предоставляет функции изменения данных о вакансиях в БД
 */

public class VacancyDBWriter extends DBConnection implements VacancyWriterInt {

    private final String INSERT = "insert into vacancy " +
            "(id, vacancyname, vacancysalary, vacancyexperience, vacancyarea, url) " +
            "values (?, ?, ?,?,?,?);";
    private final String DELETE = "delete from vacancy where id = ?";
    private final String UPDATE =
            "update vacancy SET " +
                    " ,vacancyname = ?  " +
                    " ,vacancysalary = ? " +
                    " ,vacancyexperience = ? " +
                    " ,vacancyarea = ? " +
                    "WHERE id = ? ";


    /**
     * Записывает вакансию в БД
     *
     * @param list список вакансий для заненсения в БД
     */
    @Override
    public void insert(Collection<Vacancy> list) {

        Connection conn = null;
        try {
            conn = getConnection();

            PreparedStatement ps = conn.prepareStatement(INSERT);
            for (Vacancy vacancy : list) {
                ps.setString(1, vacancy.getId());
                ps.setString(2, vacancy.getVacancyName());
                ps.setString(3, vacancy.getVacancySalary());
                ps.setString(4, vacancy.getVacancyExperience());
                ps.setString(5, vacancy.getVacancyArea());
                ps.setString(6, vacancy.getUrl());
                ps.executeUpdate();
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    /**
     * Удаляет вакнсию из БД
     *
     * @param id идентификатор удаляемой вакансии
     */
    @Override
    public void deleteVacancy(String id) {

        Connection conn = null;
        try {
            conn = getConnection();

            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    /**
     * Обновляет вакансию
     *
     * @param vacancy обновляемая вакансия
     */
    @Override
    public void updateVacancy(Vacancy vacancy) {

        Connection conn = null;
        try {
            conn = getConnection();

            PreparedStatement ps = conn.prepareStatement(UPDATE);

            ps.setString(1, vacancy.getVacancyName());
            ps.setString(2, vacancy.getVacancySalary());
            ps.setString(3, vacancy.getVacancyExperience());
            ps.setString(4, vacancy.getVacancyArea());
            ps.setString(5, vacancy.getId());

            ps.executeUpdate();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
