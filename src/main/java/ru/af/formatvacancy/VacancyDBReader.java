package ru.af.formatvacancy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Чтение вакансий из БД
 */

public class VacancyDBReader implements VacancyReaderInt {

    private static final String QUERY = "select * from vacancy";

    /**
     * Получение всех вакансий из БД
     *
     * @return listOfVacancies
     */
    @Override
    public List<Vacancy> getAllVacancies() {
        Connection conn = null;
        ArrayList<Vacancy> listOfVacancies = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(PropertyHolder.getInstance().DB_URL,
                    PropertyHolder.getInstance().USER, PropertyHolder.getInstance().PASSWORD);
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

    /**
     * кто бы знал
     *
     * @param number
     * @return
     */
    @Override
    public List<Vacancy> getVacancies(int number) {
        //todo: implement
        return null;
    }
}
