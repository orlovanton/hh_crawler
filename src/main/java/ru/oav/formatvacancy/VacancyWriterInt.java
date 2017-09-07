package ru.oav.formatvacancy;

import ru.oav.entity.HhVacancy;

import java.util.List;

/**
 * Created by PC on 17.06.2017.
 */
public interface VacancyWriterInt {

    void writeHhVacancy(List<HhVacancy> list);

    void insert(List<Vacancy> list);

}
