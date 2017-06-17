package ru.oav.formatvacancy;

import java.util.List;

/**
 * Created by antonorlov on 17/06/2017.
 */
public interface VacancyReaderInt {

    /**
     * Получить список всех вакансий из файла
     *
     * @return
     */
    List<Vacancy> getAllVacancies();
}
