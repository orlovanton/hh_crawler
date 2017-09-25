package ru.oav.dao;

import java.util.Collection;

/**
 * Created by PC on 17.06.2017.
 */
public interface VacancyWriter {

    void insert(Collection<Vacancy> list);

    void updateVacancy(Vacancy vacancy);

    void deleteVacancy(String id);

    void deleteAll();

}
