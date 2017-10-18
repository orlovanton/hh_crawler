package ru.af.formatvacancy;

import ru.af.entity.HhVacancy;
import ru.af.json.VacancyUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Позволяет записать вакансии или лист вакансий в txt фаил
 */
public class VacancyTxtWriter implements VacancyWriterInt {

    /**
     * Записывает лист вакнсий в txt файл
     *
     * @param list список вакансий, полученных из VacancyUtil
     */
    public void writeHhVacancy(List<HhVacancy> list) {
        List<Vacancy> result = new ArrayList<>();
        for (HhVacancy hhVacancy : list) {
            result.add(VacancyUtil.convert(hhVacancy));
        }
        insert(result);
    }

    /**
     * Добавляет вакансию в фаил
     * @param list
     */
    public void insert(Collection<Vacancy> list) {
        File vacancies = new File(PropertyHolder.getInstance().PATHFILE);
        PrintWriter out = null;

        try {
            if (!vacancies.exists()) {
                vacancies.createNewFile();
            }

            out = new PrintWriter(vacancies.getAbsoluteFile());
            String separator = "@@@";
            for (Vacancy convert : list) {
                StringBuilder sb = new StringBuilder("");
                sb.append(convert.getId());
                sb.append(separator);
                sb.append(convert.getVacancyName());
                sb.append(separator);
                sb.append(convert.getVacancyArea());
                sb.append(separator);
                sb.append(convert.getVacancyExperience());
                sb.append(separator);
                sb.append(convert.getVacancySalary());
                sb.append(System.lineSeparator());
                out.print(sb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    public void updateVacancy(Vacancy vacancy) {

    }

    @Override
    public void deleteVacancy(String id) {

    }
}
