package ru.oav.dao;

import java.util.Collection;

/**
 * Created by antonorlov on 17/06/2017.
 */
public interface VacancyReader {

    /**
     * Получить список всех вакансий
     *
     * @return
     */
    Collection<Vacancy> getAllVacancies();


    /**
     * Получить страницу
     * @param page номер страницы
     * @param pageSize размер страницы
     * @return
     */
    Collection<Vacancy> getVacancies(int page, int pageSize);

    /**
     * Получить кол-во сохраненных вакансий
     * @return
     */
    int getTotal();
}
