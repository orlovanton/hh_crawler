package ru.af.formatvacancy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Чтение вакансий из txt файла
 */
public class VacancyTxtReader implements VacancyReaderInt {


    /**
     * Получает список ваканский из txt файла. Путь файла-PATHFILE
     * @return список ваканский, загруженных из файла
     */
    public List<Vacancy> getAllVacancies() {
        List<Vacancy> listOfVacancies = new ArrayList<>();

        try {
            FileInputStream fstream = new FileInputStream(PropertyHolder.PATHFILE);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().length() == 0) {
                    continue;
                }
                Vacancy vacancy = new Vacancy();
                //разбивка по полям разделителем @@@
                String[] fields = line.split("@@@");
                vacancy.setId(fields[0]);
                vacancy.setVacancyName(fields[1]);
                vacancy.setVacancyArea(fields[2]);
                vacancy.setVacancyExperience(fields[3]);
                vacancy.setVacancySalary(fields[4]);
                listOfVacancies.add(vacancy);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listOfVacancies;
    }

    @Override
    public List<Vacancy> getVacancies(int number) {
//        todo: impl
        return null;
    }
}
