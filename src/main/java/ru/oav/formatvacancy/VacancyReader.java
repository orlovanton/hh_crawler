package ru.oav.formatvacancy;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by PC on 17.06.2017.
 */
public class VacancyReader implements VacancyReaderInt {


    /**
     * чтение списка ваканский из файла (путь PATHFILE)
     *
     * @return список ваканский, загруженных из файла
     */
    public List<Vacancy> getAllVacancies() {
        List<Vacancy> listOfVacancies = new ArrayList<>();

        try {
            FileInputStream fstream = new FileInputStream(Constanses.PATHFILE);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().length() == 0) {
                    continue;
                }
                Vacancy vacancy = new Vacancy();

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
}
