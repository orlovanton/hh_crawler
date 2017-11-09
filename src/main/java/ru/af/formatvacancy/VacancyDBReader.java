package ru.af.formatvacancy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Чтение вакансий из БД
 */

public class VacancyDBReader extends DBConnection implements VacancyReaderInt {

    private static final String QUERY = "select * from vacancy";

    /**
     * Получить все вакансий из БД
     *
     * @return список всех вакансий, полученный из БД
     */
    @Override
    public List<Vacancy> getAllVacancies() {

        ArrayList<Vacancy> listOfVacancies = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            //передается select-запрос
            PreparedStatement ps = conn.prepareStatement(QUERY);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vacancy vacancy = new Vacancy(
                        rs.getString("id"),
                        rs.getString("vacancyName"),
                        rs.getString("vacancySalary"),
                        rs.getString("vacancyExperience"),
                        rs.getString("vacancyArea")
                );
                listOfVacancies.add(vacancy);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return listOfVacancies;
    }


    @Override
    public List<Vacancy> getVacancies(int number) {
        //todo: implement
        return null;
    }
}
