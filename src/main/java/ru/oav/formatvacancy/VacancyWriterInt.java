package ru.oav.formatvacancy;

import ru.oav.entity.HhVacancy;

import java.util.Collection;
import java.util.List;

/**
 * Created by PC on 17.06.2017.
 */
public interface VacancyWriterInt {

    void writeHhVacancy(List<HhVacancy> list);

    void insert(Collection<Vacancy> list);

    void deleteVacancy(String id);

    void updateVacancy(Vacancy vacancy);

}
