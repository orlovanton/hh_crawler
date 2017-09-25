package ru.oav.dao;

import ru.oav.util.PropertyHolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * Конвертирует класс HhVacancy в класс Vacancy (поля класса: город, опыт и з/п вакансии)
 * Записывает лист вакнский, полученных из VacancyUtil (метод getVacancy) в txt файл
 * Created by PC on 16.06.2017.
 */
public class VacancyWriterTxt implements VacancyWriter {

    /**
     * Конвертирует класс HhVacancy в класс Vacancy
     *
     * @return Vacancy (поля класса: город, опыт и з/п вакансии)
     */


    public void insert(Collection<Vacancy> list) {
        File vacancies = new File(PropertyHolder.getInstance().getFilePath());
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
        throw new UnsupportedOperationException("not impl");
    }

    @Override
    public void deleteVacancy(String id) {
        throw new UnsupportedOperationException("not impl");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("not impl");
    }
}
