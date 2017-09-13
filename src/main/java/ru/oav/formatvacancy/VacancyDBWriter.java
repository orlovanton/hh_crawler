package ru.oav.formatvacancy;

import ru.oav.entity.HhVacancy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class VacancyDBWriter implements VacancyWriterInt {
    @Override
    public void writeHhVacancy(List<HhVacancy> list) {

    }


    @Override
    public void insert(Collection<Vacancy> list) {

        String sql = "insert into vacancy  (id, vacancyname, vacancysalary, vacancyexperience, vacancyarea) " +
                "values (?, ?, ?,?,?)";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Constanses.DB_URL,
                    Constanses.USER, Constanses.PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql);
            for (Vacancy vacancy : list) {
                ps.setString(1, vacancy.getId());
                ps.setString(2, vacancy.getVacancyName());
                ps.setString(3, vacancy.getVacancySalary());
                ps.setString(4, vacancy.getVacancyExperience());
                ps.setString(5, vacancy.getVacancyArea());
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

    @Override
    public void deleteVacancy(String id) {
        String sql = "delete from vacancy where id = ?";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Constanses.DB_URL,
                    Constanses.USER, Constanses.PASSWORD);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,id);
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

    @Override
    public void updateVacancy(Vacancy vacancy) {
        String sql;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Constanses.DB_URL,
                    Constanses.USER, Constanses.PASSWORD);
            sql = "update vacancy SET" +
                    "  ,vacancyname = ? " +
                    "  ,vacancysalary = ? " +
                    "  ,vacancyexperience = ? " +
                    "  ,vacancyarea = ?" +
                    "WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);

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
