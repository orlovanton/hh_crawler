package ru.oav.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class VacancyWriterDb extends VacancyBaseDao implements VacancyWriter {

    private static final String INSERT = "insert into vacancy " +
            "(id, vacancyname, vacancysalary, vacancyarea, employer, url) " +
            "values (?,?,?,?,?,?)";


    private static final String DELETE_BY_ID="delete from vacancy where id = ?";

    private static final String DELETE_ALL="delete from vacancy";

    @Override
    public void deleteAll() {

    }

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
                ps.setString(4, vacancy.getVacancyArea());
                ps.setString(5, vacancy.getEmployer());
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

    @Override
    public void deleteVacancy(String id) {
        Connection conn = null;
        try {
            conn = getConnection();

            PreparedStatement ps = conn.prepareStatement(DELETE_BY_ID);
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
            conn = getConnection();
            sql = "update vacancy SET" +
                    "  ,vacancyname = ? " +
                    "  ,vacancysalary = ? " +
                    "  ,vacancyarea = ? " +
                    "  ,employer = ?" +
                    "  ,url = ?" +
                    "WHERE id = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, vacancy.getVacancyName());
            ps.setString(2, vacancy.getVacancySalary());
            ps.setString(3, vacancy.getVacancyArea());
            ps.setString(4, vacancy.getEmployer());
            ps.setString(5, vacancy.getUrl());
            ps.setString(6, vacancy.getId());

            ps.executeUpdate();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
