package ru.af.formatvacancy;

import ru.af.entity.HhVacancy;

import java.util.Collection;
import java.util.List;

/**
 * Позволяет получить список вкансий, хранящихся в БД или в txt файле
 */
public interface VacancyWriterInt {

    void writeHhVacancy(List<HhVacancy> list);

    void insert(Collection<Vacancy> list);

    void deleteVacancy(String id);

    void updateVacancy(Vacancy vacancy);

}
