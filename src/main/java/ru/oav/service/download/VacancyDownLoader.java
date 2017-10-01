package ru.oav.service.download;

import ru.oav.dao.Vacancy;

import java.util.List;

/**
 * Загрузчик вакансий
 *
 * Created by antonorlov on 01/10/2017.
 */
public interface VacancyDownLoader {

    /**
     * Получить список вакансий
     *
     * @param query поисковое слово
     * @return список вакансий
     */
    List<Vacancy> download(String query);
}
