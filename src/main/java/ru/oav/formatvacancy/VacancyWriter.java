package ru.oav.formatvacancy;

import ru.oav.entity.HhVacancy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Конвертирует класс HhVacancy в класс Vacancy (поля класса: город, опыт и з/п вакансии)
 * Записывает лист вакнский, полученных из VacancyUtil (метод getVacancy) в txt файл
 * Created by PC on 16.06.2017.
 */
public class VacancyWriter implements VacancyWriterInt {

    /**
     * Конвертирует класс HhVacancy в класс Vacancy
     *
     * @param v объект вакансии
     * @return Vacancy (поля класса: город, опыт и з/п вакансии)
     */
    public Vacancy convert(HhVacancy v) {
        Vacancy shortVacancy = new Vacancy();

        if (v.getArea() != null) {
            shortVacancy.setVacancyArea(v.getArea().getName());
        } else {
            shortVacancy.setVacancyArea("Город вакансии не указан");
        }
        if (v.getExperience() != null) {
            shortVacancy.setVacancyExperience(v.getExperience().getName());
        } else {
            shortVacancy.setVacancyExperience("опыт вакансии не указан");
        }
        if (v.getSalary() != null) {
            String salary = "";
            if (v.getSalary().getFrom() != 0) {
                salary += "от " + v.getSalary().getFrom() + " ";
            }
            if (v.getSalary().getTo() != 0) {
                salary += "до " + v.getSalary().getTo();
            }
            shortVacancy.setVacancySalary(salary);
        } else {
            shortVacancy.setVacancySalary("размер з/п не указан");
        }
        shortVacancy.setVacancyName(v.getName());
        shortVacancy.setId(String.valueOf(v.getId()));

        return shortVacancy;
    }

    /**
     * запись полей объекта Vacancy в txt файл
     *
     * @param list список вакансий, полученных из VacancyUtil (метод getVacancy)
     */

    public void writeHhVacancy(List<HhVacancy> list) {
        List<Vacancy> result = new ArrayList<>();
        for (HhVacancy hhVacancy : list) {
            result.add(convert(hhVacancy));
        }
        write(result);
    }

    public void write(List<Vacancy> list) {
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
}
