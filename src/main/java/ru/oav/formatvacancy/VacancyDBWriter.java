package ru.oav.formatvacancy;

import ru.oav.entity.HhVacancy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class VacancyDBWriter implements  VacancyWriterInt {
    @Override
    public void writeHhVacancy(List<HhVacancy> list) {

    }

    @Override
    public void insert(List<Vacancy> list) {

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

}
