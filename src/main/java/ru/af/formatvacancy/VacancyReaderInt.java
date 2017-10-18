package ru.af.formatvacancy;

import java.util.List;

/**
 * Позволяет получить список вкансий, хранящихся в БД или в txt файле
 */
public interface VacancyReaderInt {

    /**
     * Получает список всех вакансий
     *
     * @return
     */
    List<Vacancy> getAllVacancies();


    List<Vacancy> getVacancies(int number);
}
