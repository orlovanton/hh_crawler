package ru.oav.dao;

import ru.oav.util.PropertyHolder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by PC on 17.06.2017.
 */
public class VacancyReaderTxt implements VacancyReader {


    /**
     * чтение списка ваканский из файла (путь PATHFILE)
     *
     * @return список ваканский, загруженных из файла
     */
    public List<Vacancy> getAllVacancies() {
        List<Vacancy> listOfVacancies = new ArrayList<>();

        try {
            FileInputStream fstream = new FileInputStream(PropertyHolder.getInstance().getFilePath());
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
                vacancy.setVacancySalary(fields[4]);
                listOfVacancies.add(vacancy);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return listOfVacancies;
    }

    @Override
    public Collection<Vacancy> getVacancies(int page, int pageSize) {
        //todo:
        throw new UnsupportedOperationException("not impl");
    }

    @Override
    public int getTotal() {
        //todo:
        throw new UnsupportedOperationException("not impl");
    }
}
