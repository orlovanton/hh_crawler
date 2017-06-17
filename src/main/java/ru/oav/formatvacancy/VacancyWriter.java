package ru.oav.formatvacancy;

import ru.oav.entity.HhVacancy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Конвертирует класс HhVacancy в класс Vacancy (поля класса: город, опыт и з/п вакансии)
 * Записывает лист вакнский, полученных из VacancyUtil (метод getVacancy) в txt файл
 * Created by PC on 16.06.2017.
 */
public class VacancyWriter implements VacancyWriterInt {

    private static final String PATH = "C:\\Users\\PC\\Desktop\\vacanciesOut.txt";

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
            shortVacancy.setVacancySalary(v.getSalary().getFrom() + "-" + v.getSalary().getTo());
        } else {
            shortVacancy.setVacancySalary("опыт ваканскии не указан");
        }

        shortVacancy.setVacancyName(v.getName());

        return shortVacancy;
    }

    /**
     * запись прлей объекта Vacancy в txt файл
     *
     * @param list список вакансий, полученных из VacancyUtil (метод getVacancy)
     */

    public void write(List<HhVacancy> list) {
        File vacancies = new File(PATH);
        PrintWriter out = null;

        try {
            if (!vacancies.exists()) {
                vacancies.createNewFile();
            }

            out = new PrintWriter(vacancies.getAbsoluteFile());
            String separator = System.lineSeparator();
            for (HhVacancy v : list) {
                Vacancy convert = convert(v);
                StringBuilder sb = new StringBuilder("");
                sb.append("Название вакансии: ");
                sb.append(convert.getVacancyName());
                sb.append(separator);
                sb.append("Город ваканскии: ");
                sb.append(convert.getVacancyArea());
                sb.append(separator);
                sb.append("Опыт вакансии: ");
                sb.append(convert.getVacancyExperience());
                sb.append(separator);
                sb.append("З/п вакансии: ");
                sb.append(convert.getVacancySalary());
                sb.append(separator);
                sb.append(separator);
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
