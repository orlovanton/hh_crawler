package ru.oav.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * tratata
 */
public class VacancyReaderDb extends VacancyBaseDao implements VacancyReader {


    public static final String QUERY = "select * from vacancy";
    public static final String QUERY_PAGINATION = "select * from vacancy limit ? offset ?";
    public static final String QUERY_COUNT_ALL = "select count(*) as total from vacancy";

    @Override
    public Collection<Vacancy> getAllVacancies() {
        return getVacancies(QUERY);
    }

    @Override
    public Collection<Vacancy> getVacancies(int page, int pageSize) {
        if (page > 1) {
            int offset = (page - 1) * pageSize;
            return getVacancies(QUERY_PAGINATION, pageSize, offset);
        } else {
            return getVacancies(QUERY_PAGINATION, pageSize, 0);
        }
    }

    @Override
    public int getTotal() {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(QUERY_COUNT_ALL);

            if (rs.next()) {
                return rs.getInt("total");
            }

            rs.close();
            statement.close();
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
        return 0;
    }

    private Collection<Vacancy> getVacancies(final String query, int ... args) {
        Connection conn = null;
        List<Vacancy> listOfVacancies = new ArrayList<>();
        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            for (int i = 0; i < args.length; i++) {
                ps.setInt(i + 1, args[i]);

            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listOfVacancies.add(toVacancy(rs));
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


    private Vacancy toVacancy(ResultSet rs) throws SQLException {
        return new Vacancy(
                rs.getString("id"),
                rs.getString("vacancyName"),
                rs.getString("vacancyArea"),
                rs.getString("vacancySalary"),
                rs.getString("employer"),
                rs.getString("url")
        );
    }


}
