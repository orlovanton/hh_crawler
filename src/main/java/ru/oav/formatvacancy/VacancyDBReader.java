package ru.oav.formatvacancy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * tratata
 */
public class VacancyDBReader implements VacancyReaderInt {


    public static final String QUERY = "select * from vacancy";


    @Override
    public List<Vacancy> getAllVacancies() {
        Connection conn = null;
        ArrayList<Vacancy> listOfVacancies = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(Constanses.DB_URL,
                    Constanses.USER, Constanses.PASSWORD);

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
                }
            }
        }

        return listOfVacancies;
    }

}
