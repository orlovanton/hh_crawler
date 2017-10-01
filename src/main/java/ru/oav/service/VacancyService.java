package ru.oav.service;

import ru.oav.dao.*;
import ru.oav.util.PropertyHolder;

import java.util.*;

/**
 * Created by antonorlov on 16/06/2017.
 */
public class VacancyService {

    public static final String DB_MODE = "db";

    public static Collection<Vacancy> getVacancies(int page, int pageSize) {
        VacancyReader reader = getReader();
        return reader.getVacancies(page, pageSize);
    }


    public static void deleteAll() {
        getWriter().deleteAll();
    }


    public static void updateVacancies() {
        VacancyReader reader = getReader();
        VacancyWriter writer = getWriter();

        Collection<Vacancy> savedVacancies = reader.getAllVacancies();
        final String searchQuery = PropertyHolder.getInstance().getSearchQuery();

        //получить вакансии из API
        List<Vacancy> downloadedVacancies = VacancyService.downloadVacancies(searchQuery);

        Map<String, Vacancy> savedVacanciesMap = new HashMap<>();

        //добавить все вакансии в мапку
        for (Vacancy savedVacancy : savedVacancies) {
            savedVacanciesMap.put(savedVacancy.getId(), savedVacancy);
        }
        //удалить устаревшие вакансии
        for (Vacancy vacancy : downloadedVacancies) {
            if (!savedVacanciesMap.containsKey(vacancy.getId())) {
                savedVacanciesMap.remove(vacancy.getId());
                writer.deleteVacancy(vacancy.getId());
            }
        }
        Set<Vacancy> newVacancies = new HashSet<>();
        List<Vacancy> updatedVacancies = new ArrayList<>();
        for (Vacancy downloadedVacancy : downloadedVacancies) {
            if (savedVacanciesMap.containsKey(downloadedVacancy.getId())) {
                if (!downloadedVacancy.equals(savedVacanciesMap.get(downloadedVacancy.getId()))) {
                    updatedVacancies.add(downloadedVacancy);
                }
            } else {
                newVacancies.add(downloadedVacancy);
            }

        }

        //записать мапку в файл или в БД
        writer.insert(newVacancies);
        for (Vacancy vacancy : updatedVacancies) {
            writer.updateVacancy(vacancy);
        }
    }

    /**
     * Получить список вакансий
     *
     * @param query поисковое слово, например java
     * @return список вакансий
     */
    public static List<Vacancy> downloadVacancies(String query) {
        return VacancyUtil.downloadVacancies(query,-1);
    }

    /**
     * Кол-во страниц сохраненных записей
     * @param perPage элементов на странице
     * @return
     */
    public static int getTotalPages(int perPage) {
        return getReader().getTotal() / perPage;
    }


    /**
     * Реализация чтения из файла или БД
     * @return
     */
    private static VacancyReader getReader() {
        if (DB_MODE.equals(PropertyHolder.getInstance().getMode())) {
            return new VacancyReaderDb();
        } else {
            return new VacancyReaderTxt();
        }
    }

    private static VacancyWriter getWriter() {
        if (DB_MODE.equals(PropertyHolder.getInstance().getMode())) {
            return new VacancyWriterDb();
        } else {
            return new VacancyWriterTxt();
        }
    }
}
