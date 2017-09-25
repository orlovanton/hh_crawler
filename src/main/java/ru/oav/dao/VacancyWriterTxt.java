package ru.oav.dao;

import ru.oav.entity.HhVacancy;
import ru.oav.formatvacancy.Constanses;
import ru.oav.json.VacancyUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Конвертирует класс HhVacancy в класс Vacancy (поля класса: город, опыт и з/п вакансии)
 * Записывает лист вакнский, полученных из VacancyUtil (метод getVacancy) в txt файл
 * Created by PC on 16.06.2017.
 */
public class VacancyWriterTxt implements VacancyWriter {

    /**
     * Конвертирует класс HhVacancy в класс Vacancy
     *
     * @param v объект вакансии
     * @return Vacancy (поля класса: город, опыт и з/п вакансии)
     */


    /**
     * запись полей объекта Vacancy в txt файл
     *
     * @param list список вакансий, полученных из VacancyUtil (метод getVacancy)
     */

    public void writeHhVacancy(List<HhVacancy> list) {
        List<Vacancy> result = new ArrayList<>();
        for (HhVacancy hhVacancy : list) {
            result.add(VacancyUtil.convert(hhVacancy));
        }
        insert(result);
    }

    public void insert(Collection<Vacancy> list) {
        File vacancies = new File(Constanses.PATHFILE);
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
